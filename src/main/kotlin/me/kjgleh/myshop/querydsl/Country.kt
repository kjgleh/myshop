package me.kjgleh.myshop.querydsl

import javax.persistence.*

@Entity
class Country(
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val continent: Continent
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "country")
    val cities = mutableListOf<City>()
}
