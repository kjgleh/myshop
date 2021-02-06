package me.kjgleh.myshop.domain

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
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

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(Book::id)
        private val toStringProperties= arrayOf(
            Book::id,
            Book::title,
            Book::uuid
        )
    }

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)
}