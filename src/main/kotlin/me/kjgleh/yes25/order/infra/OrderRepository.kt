package me.kjgleh.yes25.order.infra

import me.kjgleh.yes25.order.command.domain.Order
import me.kjgleh.yes25.order.command.domain.OrderNo
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, OrderNo>,
    OrderRepositoryCustom