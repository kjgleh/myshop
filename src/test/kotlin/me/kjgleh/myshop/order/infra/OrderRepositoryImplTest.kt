package me.kjgleh.myshop.order.infra

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.config.QuerydslTestConfiguration
import me.kjgleh.myshop.order.command.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest

@DataJpaTest
@Import(QuerydslTestConfiguration::class)
internal class OrderRepositoryImplTest @Autowired constructor(
    private val orderRepository: OrderRepository
) {

    private val kotlinFixture = kotlinFixture()

    @Test
    fun `select by orderer`() {
        // Arrange
        val orders = (0..10).map {
            kotlinFixture<Order>()
        }
        orderRepository.saveAll(orders)

        // Act
        val sut = orderRepository
        val pageSize = 10
        val pageNumber = 0
        val actual = sut.selectByOrderer(
            ordererId = orders.first().orderer.memberId.id,
            pageable = PageRequest.of(pageNumber, pageSize)
        )

        // Assert
        assertThat(actual.totalElements).isEqualTo(1)
        val orderSaved = actual.content.first()
        assertThat(orderSaved.ordererName).isEqualTo(orders.first().orderer.name)
    }
}
