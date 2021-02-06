package me.kjgleh.myshop.member.domain

import javax.persistence.Embeddable

@Embeddable
class Password(
    val value: String
)