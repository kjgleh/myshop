package me.kjgleh.myshop.order.command.dto

data class ShippingInfo(
    val address: Address,
    val message: String?,
    val receiver: Receiver
) {

    data class Address(
        val zipCode: String,
        val address1: String,
        val address2: String
    )

    data class Receiver(
        val name: String,
        val phone: String
    )
}
