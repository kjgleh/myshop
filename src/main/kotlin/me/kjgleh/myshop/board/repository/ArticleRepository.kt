package me.kjgleh.myshop.board.repository

import me.kjgleh.myshop.board.domain.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long>