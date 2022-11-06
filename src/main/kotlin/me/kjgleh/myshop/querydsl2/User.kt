package me.kjgleh.myshop.querydsl2

import javax.persistence.*

@Entity
class User(
    val name: String,
    val age: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}
