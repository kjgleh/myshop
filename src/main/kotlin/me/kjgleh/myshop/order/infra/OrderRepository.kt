package me.kjgleh.myshop.order.infra

import me.kjgleh.myshop.order.command.domain.Order
import me.kjgleh.myshop.order.command.domain.OrderNo
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, OrderNo>,
    OrderRepositoryCustom