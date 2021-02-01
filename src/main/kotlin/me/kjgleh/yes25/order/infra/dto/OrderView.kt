package me.kjgleh.yes25.order.infra.dto

data class OrderView constructor(
    val number: String,
    val ordererId: String,
    val ordererName: String,
    val orderLines: List<OrderLine>
)

data class OrderLine constructor(
    val productId: Long,
    val quantity: Int
)
