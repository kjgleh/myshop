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
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
internal class BookRepositoryTest @Autowired constructor(
    private val publisherRepository: PublisherRepository,
    private val bookRepository: BookRepository,
    private val entityManager: TestEntityManager
) {

    private val fixture = kotlinFixture {
        recursionStrategy(NullRecursionStrategy)
//        Publisher Entity 생성 시 id가 0이 아니면 저장된 DB에 저장된 id와 영속성 컨텍스트의 id가 달라서
//        Book 저장 시 ConstraintViolationException 이 발생한다.
//        기본값을 0으로 하면 해당 문제를 회피할 수 있다.
        property(Publisher::id) { 0 }
        property(Book::id) { 0 }
    }

    private lateinit var publisher: Publisher

    @BeforeEach
    fun setup() {
        publisher = fixture()
        publisherRepository.save(publisher)
    }

    @Test
    fun `find book correctly`() {
        // Arrange
        val book = fixture<Book> {
            factory<Publisher> { publisher }
        }

        // Act
        bookRepository.save(book)
        entityManager.flush()
        entityManager.clear()

        // Assert
        val expected = bookRepository.findByIdOrNull(book.id)
        assertThat(expected).isNotNull
        assertThat(expected?.title).isEqualTo(book.title)
    }

    @Test
    fun `find book with publisher correctly`() {
        // Arrange
        val book = fixture<Book> {
            factory<Publisher> { publisher }
        }

        // Act
        bookRepository.save(book)
        entityManager.flush()
        entityManager.clear()

        // Assert
        val bookSaved = bookRepository.findByIdOrNull(book.id)
        assertThat(bookSaved).isNotNull
        assertThat(bookSaved?.title).isEqualTo(book.title)
        assertThat(bookSaved?.publisher?.name).isEqualTo(book.publisher.name)
    }
}