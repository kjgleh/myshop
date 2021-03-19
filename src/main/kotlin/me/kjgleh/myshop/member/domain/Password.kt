package me.kjgleh.myshop.member.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Password(
    @Column(name = "password")
    val value: String
)