package me.kjgleh.yes25.order.controller

import me.kjgleh.yes25.order.command.dto.OrderRequest
import me.kjgleh.yes25.order.controller.exception.OrderProductEmptyException

object OrderRequestValidator {

    fun validate(orderRequest: OrderRequest) {
        if (orderRequest.orderProducts.isEmpty()) {
            throw IllegalArgumentException(OrderProductEmptyException())
        }
    }
}