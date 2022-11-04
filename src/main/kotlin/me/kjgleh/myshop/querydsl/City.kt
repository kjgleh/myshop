package me.kjgleh.myshop.querydsl

import javax.persistence.*

@Entity
class City(
    val name: String,
    @ManyToOne
    val country: Country
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}
