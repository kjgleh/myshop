package me.kjgleh.yes25.repository

import me.kjgleh.yes25.domain.Publisher
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PublisherRepository: JpaRepository<Publisher, Long> {

    //    @Query("select DISTINCT p from yes25_publishers p join fetch p.books")
    @Query("select p from yes25_publishers p")
    @EntityGraph(attributePaths = ["books"])
    fun findAllBookTitle(): List<Publisher>
}