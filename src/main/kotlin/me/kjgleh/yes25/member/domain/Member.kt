package me.kjgleh.yes25.member.domain

import javax.persistence.Embedded
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @EmbeddedId
    val id: MemberId,

    val name: String,
    @Embedded
    val password: Password
)