package me.kjgleh.yes25.board.domain

import javax.persistence.Embeddable

@Embeddable
class ArticleContent(
    val content: String,
    val contentType: String
)
