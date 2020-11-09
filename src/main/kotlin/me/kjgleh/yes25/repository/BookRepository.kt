package me.kjgleh.yes25.repository

import me.kjgleh.yes25.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository : JpaRepository<Book, Long>