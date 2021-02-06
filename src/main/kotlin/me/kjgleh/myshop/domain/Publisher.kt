package me.kjgleh.myshop.domain

import javax.persistence.*

@Entity(name = "yes25_publishers")
class Publisher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String
) {
    @OneToMany(mappedBy = "publisher")
    val books: MutableList<Book> = mutableListOf()
}