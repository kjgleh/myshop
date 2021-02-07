package me.kjgleh.myshop.order.infra

import me.kjgleh.myshop.order.command.domain.Order
import me.kjgleh.myshop.order.command.domain.OrderNo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<Order, Long>,
    OrderRepositoryCustom {
    fun findByOrderNo(orderNo: OrderNo): Optional<Order>
}