package me.kjgleh.myshop.order.command.dto

data class OrderRequest(
    val orderProducts: List<OrderProduct>,
    val orderer: Orderer,
    val shippingInfo: ShippingInfo
)
