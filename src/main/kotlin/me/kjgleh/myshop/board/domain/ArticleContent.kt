package me.kjgleh.myshop.board.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ArticleContent(
    @Column(table = "article_content")
    val content: String,

    @Column(table = "article_content")
    val contentType: String
)
