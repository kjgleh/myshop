package me.kjgleh.myshop.board.domain

import javax.persistence.Embeddable

@Embeddable
class ArticleContent(
    val content: String,
    val contentType: String
)
