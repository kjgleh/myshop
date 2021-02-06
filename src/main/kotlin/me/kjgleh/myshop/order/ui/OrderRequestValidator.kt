package me.kjgleh.myshop.order.ui

import me.kjgleh.myshop.order.command.dto.OrderRequest
import me.kjgleh.myshop.order.ui.exception.OrderProductEmptyException

object OrderRequestValidator {

    fun validate(orderRequest: OrderRequest) {
        if (orderRequest.orderProducts.isEmpty()) {
            throw IllegalArgumentException(OrderProductEmptyException())
        }
    }
}