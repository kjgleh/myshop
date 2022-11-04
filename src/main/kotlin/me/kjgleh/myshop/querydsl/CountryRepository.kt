package me.kjgleh.myshop.querydsl

import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository :
    JpaRepository<Country, Long>,
    CountryRepositoryCustom
