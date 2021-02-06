package me.kjgleh.myshop.order.command.domain

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import java.io.Serializable
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderNo(
    @Column(name = "order_number")
    val number: String
) : Serializable {

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(OrderNo::number)

        fun nextOrderNo(): OrderNo {
            val randomNo = ThreadLocalRandom.current().nextInt(900000) + 100000
            val number = String.format("%tY%<tm%<td%<tH-%d", Date(), randomNo)
            return OrderNo(number)
        }
    }

    override fun equals(other: Any?) = kotlinEquals(
        other = other,
        properties = equalsAndHashCodeProperties
    )

    override fun hashCode() =
        kotlinHashCode(properties = equalsAndHashCodeProperties)
}
