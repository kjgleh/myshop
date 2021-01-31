package me.kjgleh.yes25.order.ui

import me.kjgleh.yes25.order.command.dto.OrderRequest
import me.kjgleh.yes25.order.ui.exception.OrderProductEmptyException

object OrderRequestValidator {

    fun validate(orderRequest: OrderRequest) {
        if (orderRequest.orderProducts.isEmpty()) {
            throw IllegalArgumentException(OrderProductEmptyException())
        }
    }
}