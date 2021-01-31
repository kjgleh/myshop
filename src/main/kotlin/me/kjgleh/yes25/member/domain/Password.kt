package me.kjgleh.yes25.member.domain

import javax.persistence.Embeddable

@Embeddable
class Password(
    val value: String
)