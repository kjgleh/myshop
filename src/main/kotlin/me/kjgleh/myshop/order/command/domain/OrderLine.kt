package me.kjgleh.myshop.order.command.domain

import me.kjgleh.myshop.common.model.Money
import javax.persistence.Embeddable

@Embeddable
class OrderLine(
    val productId: Long,

    @Suppress("JpaAttributeTypeInspection")
    val price: Money,

    val quantity: Int,

    @Suppress("JpaAttributeTypeInspection")
    val amounts: Money
) {
    companion object {
        fun of(productId: Long, price: Money, quantity: Int): OrderLine {
            return OrderLine(
                productId = productId,
                price = price,
                quantity = quantity,
                amounts = price.multiply(quantity)
            )
        }
    }
}