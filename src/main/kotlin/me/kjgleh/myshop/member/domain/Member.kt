package me.kjgleh.myshop.member.domain

import javax.persistence.*

@Entity(name = "myshop_member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val memberId: MemberId,

    var name: String,

    @Embedded
    val password: Password
) {

    fun update(name: String) {
        this.name = name
    }
}
