package me.kjgleh.myshop.querydsl

import com.querydsl.core.annotations.QueryProjection

data class CountryAndContinentDto @QueryProjection constructor(
    val countryId: Long,
    val countryName: String,
    val continentName: String
)
