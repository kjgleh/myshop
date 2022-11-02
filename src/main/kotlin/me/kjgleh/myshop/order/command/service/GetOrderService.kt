package me.kjgleh.myshop.order.command.service

import me.kjgleh.myshop.order.infra.OrderRepository
import org.springframework.stereotype.Service

@Service
class GetOrderService(
    private val orderRepository: OrderRepository
) {

    fun getOrders() {
        val orders = orderRepository.findAll()
    }
}