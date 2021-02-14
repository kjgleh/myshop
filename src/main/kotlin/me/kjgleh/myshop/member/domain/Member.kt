package me.kjgleh.myshop.member.domain

import javax.persistence.*

@Entity(name = "myshop_member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val memberId: MemberId,

    val name: String,

    @Embedded
    val password: Password
)