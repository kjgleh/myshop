package me.kjgleh.yes25.order.command.repository

import me.kjgleh.yes25.order.command.domain.Order
import me.kjgleh.yes25.order.command.domain.OrderNo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface OrderRepository : JpaRepository<Order, OrderNo>