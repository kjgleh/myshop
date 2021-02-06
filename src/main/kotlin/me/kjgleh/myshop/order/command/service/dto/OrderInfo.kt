package me.kjgleh.myshop.order.command.service.dto

import me.kjgleh.myshop.order.command.domain.OrderLine
import me.kjgleh.myshop.order.command.domain.OrderNo
import me.kjgleh.myshop.order.command.dto.OrderRequest

data class OrderInfo(
    val orderNo: OrderNo,
    val orderRequest: OrderRequest,
    val orderLines: List<OrderLine>
)