package me.kjgleh.yes25.domain

import java.util.*
import javax.persistence.*

@Entity(name = "yes25_books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val publisher: Publisher
) {
    val uuid: String = UUID.randomUUID().toString()
}