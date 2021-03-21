package me.kjgleh.myshop.member.exception

import me.kjgleh.myshop.common.exception.ServiceBadRequestException

class InvalidTokenException(message: String = "Invalid token") :
    ServiceBadRequestException(message)
