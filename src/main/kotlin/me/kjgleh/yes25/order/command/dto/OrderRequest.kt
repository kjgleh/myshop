package me.kjgleh.yes25.order.command.dto

data class OrderRequest(
    val orderProducts: List<OrderProduct>,
    val orderer: Orderer,
    val shippingInfo: ShippingInfo
)
