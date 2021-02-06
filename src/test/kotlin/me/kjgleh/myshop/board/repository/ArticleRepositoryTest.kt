package me.kjgleh.myshop.board.repository

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.board.domain.Article
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
internal class ArticleRepositoryTest @Autowired constructor(
    private val articleRepository: ArticleRepository,
    private val entityManager: TestEntityManager
) {

    companion object {
        private val kotlinFixture = kotlinFixture()
    }

    @Test
    fun `save article correctly`() {
        // Arrange
        val article = kotlinFixture<Article>()

        // Act
        val sut = articleRepository
        sut.save(article)

        entityManager.flush()
        entityManager.clear()

        // Assert
        val actual = sut.findAll()
        assertThat(actual.size).isEqualTo(1)
    }

}