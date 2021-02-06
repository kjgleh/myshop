package me.kjgleh.myshop.order.command.dto

data class OrderMaterial(
    val orderProducts: List<OrderProduct>,
    val orderer: Orderer,
    val shippingInfo: ShippingInfo
)
