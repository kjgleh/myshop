package me.kjgleh.myshop.member.domain

import javax.persistence.*

@Entity(name = "myshop_member")
class Member(
    val memberId: MemberId,

    @Embedded
    val password: Password,

    name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var name: String = name
        protected set

    fun update(name: String) {
        this.name = name
    }
}
