package me.kjgleh.yes25.order.command.domain

import me.kjgleh.yes25.member.domain.MemberId
import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Orderer(
    @AttributeOverride(name = "id", column = Column(name = "order_id"))
    val memberId: MemberId,

    @Column(name = "orderer_name")
    val name: String
)