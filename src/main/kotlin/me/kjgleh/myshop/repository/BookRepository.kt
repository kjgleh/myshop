package me.kjgleh.myshop.repository

import me.kjgleh.myshop.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByUuid(uuid: String): Book?
}