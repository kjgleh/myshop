package me.kjgleh.myshop.board.repository

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.board.domain.Article
import me.kjgleh.myshop.config.QuerydslTestConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(QuerydslTestConfiguration::class)
internal class ArticleRepositoryTest @Autowired constructor(
    private val articleRepository: ArticleRepository
) {

    private val fixture = kotlinFixture()

    @Test
    fun `save article correctly`() {
        // Arrange
        val article = fixture<Article>()

        // Act
        val sut = articleRepository
        sut.save(article)

        // Assert
        val actual = sut.findAll()
        assertThat(actual.size).isEqualTo(2)
    }

}
