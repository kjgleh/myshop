package me.kjgleh.myshop.common.exception

import me.kjgleh.myshop.order.ui.exception.OrderProductEmptyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ServiceError {
        return ServiceError(
            code = e.javaClass.simpleName,
            message = e.message.orEmpty()
        )
    }

    @ExceptionHandler(ServiceBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(e: ServiceBadRequestException): ServiceError {
        return ServiceError(
            code = e.javaClass.simpleName,
            message = e.message.orEmpty()
        )
    }

    @ExceptionHandler(ServiceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(e: ServiceNotFoundException): ServiceError {
        return ServiceError(
            code = e.javaClass.simpleName,
            message = e.message.orEmpty()
        )
    }
}