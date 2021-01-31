package me.kjgleh.yes25.order.ui.exception

class OrderProductEmptyException(override val message: String = "OrderProduct is empty") :
    Exception(message)
