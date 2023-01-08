package me.kjgleh.myshop.querydsl

import com.querydsl.core.Tuple

interface CountryRepositoryCustom {

    fun findByName(name: String): Country?
    fun findTupleAndContinentByIdAndCountryName(id: Long, name: String): Tuple?
    fun findCountry1(countryId: Long): CountryAndContinentDto?
    fun findByContinent(continentId: Long): List<Country>
    fun findByContinent2(continentId: Long): List<Country>
    fun findByContinent3(continentId: Long): Continent?
    fun findByContinent4(continentId: Long): Continent?
}
