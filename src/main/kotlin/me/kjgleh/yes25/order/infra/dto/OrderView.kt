package me.kjgleh.yes25.order.infra.dto

import com.querydsl.core.annotations.QueryProjection

data class OrderView @QueryProjection constructor(
    val number: String,
    val ordererId: String,
    val ordererName: String
//    val orderLines: List<OrderLine>
)

data class OrderLine constructor(
    val productId: String,
    val quantity: Int
)
