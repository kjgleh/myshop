package me.kjgleh.yes25.order.infra.dto

data class OrderView(
    val number: String,
    val ordererId: String,
    val ordererName: String,
    val orderLines: List<OrderLine>
)

data class OrderLine(
    val productId: Long,
    val quantity: Int
)
