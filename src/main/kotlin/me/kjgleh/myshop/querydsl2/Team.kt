package me.kjgleh.myshop.querydsl2

import javax.persistence.*

@Entity
class Team(
    val name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "team")
    val users = mutableListOf<User>()
}
