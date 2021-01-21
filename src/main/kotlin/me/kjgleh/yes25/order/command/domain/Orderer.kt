package me.kjgleh.yes25.order.command.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Orderer(
    @Column(name = "orderer_id")
    val memberId: String,

    @Column(name = "orderer_name")
    val name: String
)