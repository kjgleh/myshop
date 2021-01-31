package me.kjgleh.yes25.common.infra

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Expression
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.querydsl.SimpleEntityPathResolver
import org.springframework.data.repository.support.PageableExecutionUtils
import javax.persistence.EntityManager

abstract class QuerydslRepositorySupportCustom(
    val entityManager: EntityManager,
    domainClass: Class<*>
) {

    private var querydsl: Querydsl
    private var queryFactory: JPAQueryFactory

    init {
        val entityInformation =
            JpaEntityInformationSupport.getEntityInformation(
                domainClass,
                entityManager
            )
        val simpleEntityPathResolver = SimpleEntityPathResolver.INSTANCE
        val entityPath =
            simpleEntityPathResolver.createPath(entityInformation.javaType)
        querydsl = Querydsl(
            entityManager,
            PathBuilder(entityPath.type, entityPath.metadata)
        )
        queryFactory = JPAQueryFactory(entityManager)
    }

    protected fun getQueryFactory() = queryFactory
    protected fun getQuerydsl() = querydsl
    protected fun <T> select(expr: Expression<T>?): JPAQuery<T> =
        getQueryFactory().select(expr)

    protected fun <T> selectFrom(from: EntityPath<T>?): JPAQuery<T> =
        getQueryFactory().selectFrom(from)

    protected fun <T> getContent(
        pageable: Pageable,
        contentQuery: (JPAQueryFactory) -> JPAQuery<T>
    ): MutableList<T> {
        val jpaQuery = contentQuery(getQueryFactory())
        return getQuerydsl().applyPagination(pageable, jpaQuery).fetch()
    }

    protected fun <T> getPage(
        pageable: Pageable,
        contentQuery: (JPAQueryFactory) -> JPAQuery<T>
    ): Page<T> {
        val jpaQuery = contentQuery(getQueryFactory())
        val content: List<T> =
            getQuerydsl().applyPagination(pageable, jpaQuery).fetch()
        return PageableExecutionUtils.getPage(
            content,
            pageable,
            jpaQuery::fetchCount
        )
    }

    protected fun <T> getPage(
        pageable: Pageable,
        contentQuery: (JPAQueryFactory) -> JPAQuery<T>,
        countQuery: (JPAQueryFactory) -> JPAQuery<T>
    ): Page<T> {
        val jpaContentQuery = contentQuery(getQueryFactory())
        val content: List<T> =
            getQuerydsl().applyPagination(pageable, jpaContentQuery).fetch()
        val countResult = countQuery(getQueryFactory())
        return PageableExecutionUtils.getPage(
            content,
            pageable,
            countResult::fetchCount
        )
    }
}