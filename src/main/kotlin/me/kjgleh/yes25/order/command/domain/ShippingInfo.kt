package me.kjgleh.yes25.order.command.domain

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class ShippingInfo(
    @Column(name = "shipping_message")
    val message: String? = null,

    @Embedded
    val address: Address,

    @Embedded
    val receiver: Receiver
)