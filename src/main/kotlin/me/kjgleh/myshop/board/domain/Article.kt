package me.kjgleh.myshop.board.domain

import javax.persistence.*

@Entity
@SecondaryTable(
    name = "article_content",
    pkJoinColumns = [PrimaryKeyJoinColumn(name = "id")]
)
class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val title: String,

    @Embedded
    val articleContent: ArticleContent
)