package me.kjgleh.myshop.querydsl

import com.querydsl.core.Tuple
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kjgleh.myshop.querydsl.QContinent.continent
import me.kjgleh.myshop.querydsl.QCountry.country

class CountryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CountryRepositoryCustom {

    override fun findByName(name: String): Country? {
        return queryFactory
            .select(country)
            .from(country)
            .where(country.name.eq(name))
            .fetchOne()
    }

    // Tuple, innerJoin
    override fun findTupleAndContinentByCountryName(name: String): Tuple? {
        val tuple = queryFactory
            .select(country.id, country.name, continent)
            .from(country)
            .innerJoin(country.continent, continent)
            .where(country.name.eq(name))
            .fetchOne()

        println(tuple?.get(country.id))
        println(tuple?.get(continent)?.name)

        return tuple
    }

    // leftJoin
    override fun findByContinent(continentId: Long): List<Country> {
        return queryFactory
            .select(country)
            .from(continent)
            .leftJoin(continent.countries, country)
            .where(continent.id.eq(continentId))
            .fetch()
    }

    // 연관관계가 없을 때 join
    override fun findByContinent2(continentId: Long): List<Country> {
        return queryFactory
            .select(country)
            .from(continent)
            .join(country).on(continent.id.eq(country.continent.id))
            .where(continent.id.eq(continentId))
            .fetch()
    }

    // 1:N 관계 조회
    override fun findByContinent3(continentId: Long): Continent? {
        return queryFactory
            .select(continent)
            .from(continent)
            .leftJoin(continent.countries, country)
            .where(continent.id.eq(continentId))
            .fetchOne()
    }

    // 1:N 관계 조회 with fetchjoin
    override fun findByContinent4(continentId: Long): Continent? {
        return queryFactory
            .select(continent)
            .from(continent)
            .leftJoin(continent.countries, country)
            .fetchJoin()
            .where(continent.id.eq(continentId))
            .fetchOne()
    }

    // projection: dto로 반환
    override fun findCountry1(countryId: Long): CountryAndContinentDto? {
        return queryFactory
            .select(
                QCountryAndContinentDto(
                    country.id,
                    country.name,
                    continent.name
                )
            )
            .from(country)
            .leftJoin(country.continent, continent)
            .where(country.id.eq(countryId))
            .fetchOne()
    }

    // 페이징 처리

    // 동적 쿼리

    // onetomany가 여러개일 때 fetchjoin
    // https://jojoldu.tistory.com/457?category=637935

}
