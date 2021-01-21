package me.kjgleh.yes25.order.command.service

import me.kjgleh.yes25.catalog.repository.ProductRepository
import me.kjgleh.yes25.order.command.domain.Order
import me.kjgleh.yes25.order.command.domain.OrderLine
import me.kjgleh.yes25.order.command.domain.OrderNo
import me.kjgleh.yes25.order.command.dto.OrderRequest
import me.kjgleh.yes25.order.command.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaceOrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {

    @Transactional
    fun placeOrder(
        orderNo: OrderNo,
        orderRequest: OrderRequest,
        memberName: String
    ) {
        val orderLines = orderRequest.orderProducts.map {
            val product = productRepository.findById(it.productId).orElseThrow {
                IllegalArgumentException("This product id does not exist.")
            }
            OrderLine.of(
                productId = it.productId,
                price = product.price,
                quantity = it.quantity
            )
        }
        val order = Order.of(orderNo, orderRequest, memberName, orderLines)
        orderRepository.save(order)
    }
}
