package me.kjgleh.yes25.repository

import com.appmattus.kotlinfixture.decorator.recursion.NullRecursionStrategy
import com.appmattus.kotlinfixture.decorator.recursion.recursionStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.yes25.domain.Book
import me.kjgleh.yes25.domain.Publisher
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
internal class PublisherRepositoryTest @Autowired constructor(
    private val publisherRepository: PublisherRepository,
    private val bookRepository: BookRepository,
    private val entityManager: TestEntityManager
) {

    companion object {
        private val kotlinFixture = kotlinFixture {
            recursionStrategy(NullRecursionStrategy)
        }
        private val SIZE_OF_PUBLISHER = kotlinFixture(1..3)
        private val SIZE_OF_BOOK_OF_PUBLISHER = kotlinFixture(2..4)
    }

    @BeforeEach
    fun setup() {
        val publishers = List(SIZE_OF_PUBLISHER) {
            kotlinFixture<Publisher> {
                property(Publisher::id) { 0 }
            }
        }
        publisherRepository.saveAll(publishers)

        publishers.forEach { publisher ->
            val books = List(SIZE_OF_BOOK_OF_PUBLISHER) {
                kotlinFixture<Book> {
                    property(Book::id) { 0 }
                    factory<Publisher> { publisher }
                }
            }
            bookRepository.saveAll(books)
        }

        entityManager.flush()
        entityManager.clear()
    }

    @Test
    fun `find all book title correctly`() {
        // Act
        val sut = publisherRepository
        val actual = sut.findAllBookTitle()

        // Assert
        assertThat(actual.size).isEqualTo(SIZE_OF_PUBLISHER)
    }

    @Test
    fun `find all correctly`() {
        // Act
        val sut = publisherRepository
        val actual = sut.findAll()

        // Assert
        assertThat(true)
    }
}