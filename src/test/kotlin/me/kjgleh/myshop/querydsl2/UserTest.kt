package me.kjgleh.myshop.querydsl2

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kjgleh.myshop.config.QuerydslTestConfiguration
import me.kjgleh.myshop.querydsl2.QTeam.team
import me.kjgleh.myshop.querydsl2.QUser.user
import me.kjgleh.myshop.querydsl2.dto.QUserDto2
import me.kjgleh.myshop.querydsl2.dto.UserDto2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext
import javax.persistence.PersistenceUnit

@DataJpaTest
@Import(QuerydslTestConfiguration::class)
internal class UserTest {

    @PersistenceContext
    private lateinit var em: EntityManager

    @PersistenceUnit
    private lateinit var emf: EntityManagerFactory

    @Autowired
    private lateinit var queryFactory: JPAQueryFactory

    @BeforeEach
    fun setUp() {
        val team1 = Team(name = "t1")
        val team2 = Team(name = "t2")
        em.persist(team1)
        em.persist(team2)
        em.persist(User(name = "m1", age = 10, team = team1))
        em.persist(User(name = "m2", age = 20, team = team1))
        em.persist(User(name = "m3", age = 30, team = team2))
        em.persist(User(name = "m4", age = 40, team = team2))
    }

    @Test
    fun `단 건 조회`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(
                user.name.eq("m1")
                    .and(user.age.eq(10))
            ).fetchOne()

        // Assert
        assertThat(actual?.name).isEqualTo("m1")
    }

    @Test
    fun `여러 건 조회`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(
                user.age.goe(20)
            ).fetch()

        // Assert
        assertThat(actual.size).isEqualTo(3)
    }

    @Test
    fun `정렬`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(user.age.goe(20))
            .orderBy(user.age.desc(), user.name.asc().nullsLast())
            .fetch()

        // Assert
        assertThat(actual.first().name).isEqualTo("m4")
    }

    @Test
    fun `페이징`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .orderBy(user.age.desc())
            .offset(2)
            .limit(2)
            .fetchResults()

        // Assert
        assertThat(actual.total).isEqualTo(4)
        assertThat(actual.results.size).isEqualTo(2)
        assertThat(actual.results.first().name).isEqualTo("m2")
    }

    @Test
    fun `집합`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(
                user.count(),
                user.age.sum(),
                user.age.avg(),
                user.age.max(),
                user.age.min()
            )
            .from(user)
            .fetch()

        // Assert
        val tuple = actual[0]
        assertThat(tuple.get(user.count())).isEqualTo(4)
        assertThat(tuple.get(user.age.sum())).isEqualTo(100)
        assertThat(tuple.get(user.age.min())).isEqualTo(10)
        assertThat(tuple.get(user.age.max())).isEqualTo(40)
    }

    @Test
    fun `그룹`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(team.name, user.age.sum())
            .from(user)
            .join(user.team, team)
            .groupBy(team.name)
            .fetch()

        // Assert
        val t1 = actual[0]
        assertThat(t1.get(team.name)).isEqualTo("t1")
        assertThat(t1.get(user.age.sum())).isEqualTo(30)
        val t2 = actual[1]
        assertThat(t2.get(team.name)).isEqualTo("t2")
        assertThat(t2.get(user.age.sum())).isEqualTo(70)
    }

    @Test
    fun `기본 조인`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .join(user.team, team) // inner join 과 동일함
            .where(team.name.eq("t1"))
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(2)
    }

    @Test
    fun `세타 조인`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user, team)
            .where(user.name.eq(team.name))
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(0)
    }

    @Test
    fun `on 절을 이용한 외부 조인`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .join(team).on(user.name.eq(team.name))
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(0)
    }

    @Test
    fun `페치 조인 미적용`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .join(user.team, team)
            .where(user.name.eq("m1"))
            .fetchOne()

        // Assert
        val loaded = emf.persistenceUnitUtil.isLoaded(actual?.team)
        assertThat(loaded).isTrue()
    }

    @Test
    fun `페치 조인`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .join(user.team, team).fetchJoin()
            .where(user.name.eq("m1"))
            .fetchOne()

        // Assert
        val loaded = emf.persistenceUnitUtil.isLoaded(actual?.team)
        assertThat(loaded).isTrue()
    }

    @Test
    fun `서브 쿼리 - 나이가 가장 많은 회원`() {
        // Arrange
        val userSub = QUser("userSub")

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(
                user.age.eq(
                    JPAExpressions
                        .select(userSub.age.max())
                        .from(userSub)
                )
            )
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual[0].name).isEqualTo("m4")
    }

    @Test
    fun `case`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(
                user.age
                    .`when`(10).then("열살")
                    .`when`(20).then("스무살")
                    .otherwise("기타")
            )
            .from(user)
            .fetch()

        // Assert
    }

    @Test
    fun `프로젝션 - tuple`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(user.id, user.name)
            .from(user)
            .fetch()

        // Assert
        assertThat(actual[0]).isInstanceOf(com.querydsl.core.Tuple::class.java)
    }

    @Test
    fun `프로젝션 - Projections constructor`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(
                Projections.constructor(
                    UserDto::class.java,
                    user.name,
                    user.age
                )
            )
            .from(user)
            .fetch()

        // Assert
        assertThat(actual[0]).isInstanceOf(UserDto::class.java)
    }

    @Test // type safe하지만 querydsl 의존성을 dto까지 가져가야하는 단점이 있다.
    fun `프로젝션 - @QueryProjection`() {
        // Arrange

        // Act
        val actual = queryFactory
            .select(
                QUserDto2(
                    user.name,
                    user.age
                )
            )
            .from(user)
            .fetch()

        // Assert
        assertThat(actual[0]).isInstanceOf(UserDto2::class.java)
    }

    @Test
    fun `동적 쿼리 - BooleanBuilder`() {
        // Arrange
        val nameParam: String? = "m1"
        val ageParam: Int? = 10

        val builder = BooleanBuilder()
        if (nameParam != null) {
            builder.and(user.name.eq(nameParam))
        }

        if (ageParam != null) {
            builder.and(user.age.eq(ageParam))
        }

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(builder)
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(1)
    }

    @Test
    fun `동적 쿼리 - where 다중 파라미터`() {
        // Arrange
        val nameParam: String? = "m1"
        val ageParam: Int? = 10

        // Act
        val actual = queryFactory
            .select(user)
            .from(user)
            .where(
                userNameEq(nameParam),
                userAgeEq(ageParam)
            )
            .fetch()

        // Assert
        assertThat(actual.size).isEqualTo(1)
    }

    private fun userNameEq(nameParam: String?): BooleanExpression? {
        return nameParam?.let { user.name.eq(it) }
    }

    private fun userAgeEq(ageParam: Int?): BooleanExpression? {
        return ageParam?.let { user.age.eq(it) }
    }
}

data class UserDto(
    val name: String,
    val age: Int
)
