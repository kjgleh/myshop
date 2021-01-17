package me.kjgleh.yes25.order.command.repository

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.yes25.order.command.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
internal class OrderRepositoryTest @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val entityManager: TestEntityManager
) {

    companion object {
        private val kotlinFixture = kotlinFixture()
    }

    @Test
    fun `save order correctly`() {
        // Arrange
        val order = kotlinFixture<Order>()

        // Act
        val sut = orderRepository
        sut.save(order)

        entityManager.flush()
        entityManager.clear()

        val actual = sut.findAll()

        // Assert
        assertThat(actual.size).isEqualTo(1)
    }
}