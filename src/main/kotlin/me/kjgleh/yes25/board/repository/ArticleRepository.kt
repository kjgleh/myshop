package me.kjgleh.yes25.board.repository

import me.kjgleh.yes25.board.domain.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long>