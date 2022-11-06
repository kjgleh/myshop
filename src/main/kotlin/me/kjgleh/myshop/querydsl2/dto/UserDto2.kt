package me.kjgleh.myshop.querydsl2.dto

import com.querydsl.core.annotations.QueryProjection

data class UserDto2 @QueryProjection constructor(
    val name: String,
    val age: Int
)