package me.kjgleh.yes25.order.command.service.dto

import me.kjgleh.yes25.order.command.domain.OrderLine
import me.kjgleh.yes25.order.command.domain.OrderNo
import me.kjgleh.yes25.order.command.dto.OrderRequest

data class OrderInfo(
    val orderNo: OrderNo,
    val orderRequest: OrderRequest,
    val orderLines: List<OrderLine>
)