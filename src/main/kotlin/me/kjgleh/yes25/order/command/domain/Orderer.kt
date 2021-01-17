package me.kjgleh.yes25.order.command.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Orderer(
    @Column(name = "orderer_id")
    val memberId: Long,

    @Column(name = "orderer_name")
    val name: String
)