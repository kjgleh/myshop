package me.kjgleh.myshop.querydsl

import javax.persistence.*

@Entity
class Continent(
    val name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "continent")
    val countries = mutableListOf<Country>()
}
