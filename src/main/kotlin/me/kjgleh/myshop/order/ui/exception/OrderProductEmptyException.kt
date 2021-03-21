package me.kjgleh.myshop.order.ui.exception

import me.kjgleh.myshop.common.exception.ServiceBadRequestException

class OrderProductEmptyException(message: String = "OrderProduct is empty") :
    ServiceBadRequestException(message)
