package me.kjgleh.myshop.order.command.repository

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.config.QuerydslTestConfiguration
import me.kjgleh.myshop.order.command.domain.Order
import me.kjgleh.myshop.order.infra.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(QuerydslTestConfiguration::class)
internal class OrderRepositoryTest @Autowired constructor(
    private val orderRepository: OrderRepository
) {

    private val kotlinFixture = kotlinFixture()

    @Test
    fun `save order correctly`() {
        // Arrange
        val order = kotlinFixture<Order>()

        // Act
        val sut = orderRepository
        sut.save(order)
        val actual = sut.findAll()

        // Assert
        assertThat(actual.size).isEqualTo(1)
    }
}
