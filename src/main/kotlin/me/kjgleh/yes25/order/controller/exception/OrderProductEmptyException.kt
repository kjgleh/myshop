package me.kjgleh.yes25.order.controller.exception

class OrderProductEmptyException(override val message: String = "OrderProduct is empty") :
    Exception(message)
