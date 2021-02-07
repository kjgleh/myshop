package me.kjgleh.myshop.member.domain

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val memberId: MemberId,

    val name: String,

    @Embedded
    val password: Password
)