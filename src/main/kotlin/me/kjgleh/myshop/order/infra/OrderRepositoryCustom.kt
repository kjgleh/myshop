package me.kjgleh.myshop.order.infra

import me.kjgleh.myshop.order.infra.dto.OrderView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderRepositoryCustom {
    fun selectByOrderer(
        ordererId: String,
        pageable: Pageable
    ): Page<OrderView>
}