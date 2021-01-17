package me.kjgleh.yes25.order.command.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Receiver(
    @Column(name = "receiver_name")
    val name: String,

    @Column(name = "receiver_phone")
    val phone: String
)
