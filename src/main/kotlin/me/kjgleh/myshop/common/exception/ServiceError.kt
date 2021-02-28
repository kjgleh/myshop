package me.kjgleh.myshop.common.exception

data class ServiceError(
    val code: String,
    val message: String,
    val detail: String? = null
)
