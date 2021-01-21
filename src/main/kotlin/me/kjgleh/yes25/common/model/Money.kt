package me.kjgleh.yes25.common.model

class Money(
    val value: Int
) {

    fun multiply(multiplier: Int): Money {
        return  Money(value * multiplier);
    }
}