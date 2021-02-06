package me.kjgleh.myshop.common.model

class Money(
    val value: Int
) {

    fun multiply(multiplier: Int): Money {
        return  Money(value * multiplier);
    }
}