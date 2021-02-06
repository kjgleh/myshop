package me.kjgleh.myshop.order.command.domain

import me.kjgleh.myshop.member.domain.MemberId
import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Orderer(
    @AttributeOverride(name = "id", column = Column(name = "orderer_id"))
    val memberId: MemberId,

    @Column(name = "orderer_name")
    val name: String
)