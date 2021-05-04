package me.kjgleh.myshop.order.command.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.any
import me.kjgleh.myshop.catalog.domain.product.Product
import me.kjgleh.myshop.catalog.repository.ProductRepository
import me.kjgleh.myshop.order.command.domain.Order
import me.kjgleh.myshop.order.command.domain.OrderLine
import me.kjgleh.myshop.order.command.domain.OrderNo
import me.kjgleh.myshop.order.command.dto.OrderProduct
import me.kjgleh.myshop.order.command.dto.OrderRequest
import me.kjgleh.myshop.order.command.service.dto.MemberInfo
import me.kjgleh.myshop.order.command.service.dto.OrderInfo
import me.kjgleh.myshop.order.command.service.dto.OrderResponse
import me.kjgleh.myshop.order.infra.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PlaceOrderServiceTest {

    @Mock
    private lateinit var orderRepository: OrderRepository

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMocks
    private lateinit var placeOrderService: PlaceOrderService

    private val fixture = kotlinFixture()

    @Test
    fun `placeOrder correctly`() {
        // Arrange
        val orderNo = fixture<OrderNo>()
        val orderProduct = fixture<OrderProduct>()
        val orderRequest = fixture<OrderRequest> {
            property(OrderRequest::orderProducts) {
                listOf(orderProduct)
            }
        }
        val memberInfo = fixture<MemberInfo>()
        val product = fixture<Product>()
        Mockito
            .`when`(productRepository.findById(orderProduct.productId))
            .thenReturn(
                Optional.of(product)
            )

        val orderLines = orderRequest.orderProducts.map {
            OrderLine.of(
                productId = it.productId,
                price = product.price,
                quantity = it.quantity
            )
        }

        val order = Order.of(
            OrderInfo(orderNo, orderRequest, orderLines),
            memberInfo
        )

        // Act
        val sut = placeOrderService
        val actual = sut.placeOrder(orderNo, orderRequest, memberInfo)

        // Assert
        val argumentCaptorOrder: ArgumentCaptor<Order> =
            ArgumentCaptor.forClass(Order::class.java)
        Mockito.verify(orderRepository).save(argumentCaptorOrder.capture())
        val argumentOrder = argumentCaptorOrder.value
        assertThat(argumentOrder).usingRecursiveComparison()
            .ignoringFields("orderNo")
            .isEqualTo(order)

        Mockito.verify(eventPublisher).publishEvent(any<OrderedEvent>())
        assertThat(actual).isInstanceOf(OrderResponse::class.java)
    }
}
