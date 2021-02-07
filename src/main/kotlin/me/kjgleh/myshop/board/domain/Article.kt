package me.kjgleh.myshop.board.domain

import javax.persistence.*

@Entity
@SecondaryTable(
    name = "article_content",
    pkJoinColumns = [PrimaryKeyJoinColumn(name = "seq")]
)
class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val title: String,

    @AttributeOverrides(
        AttributeOverride(
            name = "content",
            column = Column(table = "article_content")
        ),
        AttributeOverride(
            name = "contentType",
            column = Column(table = "article_content")
        )
    )
    @Embedded
    val articleContent: ArticleContent
)