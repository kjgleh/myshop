package me.kjgleh.yes25.order.command.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.any
import me.kjgleh.yes25.catalog.repository.ProductRepository
import me.kjgleh.yes25.config.IntegrationTestConfiguration
import me.kjgleh.yes25.order.command.domain.OrderNo
import me.kjgleh.yes25.order.command.dto.OrderRequest
import me.kjgleh.yes25.order.command.repository.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@Import(IntegrationTestConfiguration::class)
@SpringBootTest(classes = [PlaceOrderService::class, OrderRepository::class])
internal class PlaceOrderServiceTest @Autowired constructor(
    private val placeOrderService: PlaceOrderService,
    private val orderRepository: OrderRepository
) {

    @MockBean
    private lateinit var productRepository: ProductRepository

    companion object {
        private val kotlinFixture = kotlinFixture()
    }

    @Test
    fun `place order`() {
        // Arrange
        val orderNo = OrderNo.nextOrderNo()
        val orderRequest = kotlinFixture<OrderRequest>()
        val memberName = UUID.randomUUID().toString()

        Mockito.`when`(productRepository.findById(any())).thenReturn(
            Optional.of(kotlinFixture())
        )

        // Act
        val sut = placeOrderService
        sut.placeOrder(
            orderNo = orderNo,
            orderRequest = orderRequest,
            memberName = memberName
        )

        // Assert
        val actual = orderRepository.findByIdOrNull(orderNo)
        assertThat(actual).isNotNull
    }
}