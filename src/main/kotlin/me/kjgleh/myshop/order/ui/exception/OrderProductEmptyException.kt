package me.kjgleh.myshop.order.ui.exception

class OrderProductEmptyException(override val message: String = "OrderProduct is empty") :
    Exception(message)
