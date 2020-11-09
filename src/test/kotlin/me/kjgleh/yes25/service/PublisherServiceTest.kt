package me.kjgleh.yes25.service

import com.appmattus.kotlinfixture.decorator.recursion.NullRecursionStrategy
import com.appmattus.kotlinfixture.decorator.recursion.recursionStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.yes25.domain.Book
import me.kjgleh.yes25.domain.Publisher
import me.kjgleh.yes25.repository.BookRepository
import me.kjgleh.yes25.repository.PublisherRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
internal class PublisherServiceTest @Autowired constructor(
    private val publisherRepository: PublisherRepository,
    private val bookRepository: BookRepository,
    private val publisherService: PublisherService
) {

    companion object {
        private val kotlinFixture = com.appmattus.kotlinfixture.kotlinFixture {
            recursionStrategy(NullRecursionStrategy)
        }
        private val SIZE_OF_PUBLISHER = kotlinFixture(1..3)
        private val SIZE_OF_BOOK_OF_PUBLISHER = kotlinFixture(1..5)
//        private val SIZE_OF_PUBLISHER = 2
//        private val SIZE_OF_BOOK_OF_PUBLISHER = 0
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
    }

    @Test
    fun `find all book title correctly`() {


        // Act
//        val sut = publisherService
//        val actual = sut.findAllBookTitle()
//        println(actual)

        // Assert
        val allPublisher = publisherService.findAll()

//        assertThat(actual.size).isEqualTo(sizeOfTotalBooks)
    }
}