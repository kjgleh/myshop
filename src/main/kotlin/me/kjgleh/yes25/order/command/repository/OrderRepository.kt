package me.kjgleh.yes25.order.command.repository

import me.kjgleh.yes25.order.command.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>