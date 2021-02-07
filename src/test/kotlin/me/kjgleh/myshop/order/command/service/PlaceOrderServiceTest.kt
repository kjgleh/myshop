package me.kjgleh.myshop.order.command.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.any
import me.kjgleh.myshop.catalog.repository.ProductRepository
import me.kjgleh.myshop.config.IntegrationTestConfiguration
import me.kjgleh.myshop.member.domain.MemberId
import me.kjgleh.myshop.order.command.domain.OrderNo
import me.kjgleh.myshop.order.command.dto.OrderRequest
import me.kjgleh.myshop.order.command.service.dto.MemberInfo
import me.kjgleh.myshop.order.infra.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import java.util.*

@SpringBootTest(classes = [PlaceOrderService::class, OrderRepository::class])
@Import(IntegrationTestConfiguration::class)
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
        val orderRequest = kotlinFixture<OrderRequest>()
        val memberInfo = MemberInfo(
            id = MemberId(UUID.randomUUID().toString()),
            name = UUID.randomUUID().toString()
        )

        Mockito.`when`(productRepository.findById(any())).thenReturn(
            Optional.of(kotlinFixture())
        )

        // Act
        val sut = placeOrderService
        val orderResponse = sut.placeOrder(
            orderRequest = orderRequest,
            memberInfo = memberInfo
        )

        // Assert
        val actual =
            orderRepository.findByOrderNo(OrderNo(orderResponse.orderNo))
        assertThat(actual).isNotEmpty
    }
}