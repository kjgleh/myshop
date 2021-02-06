package me.kjgleh.myshop.order.command.domain

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import me.kjgleh.myshop.order.command.service.dto.MemberInfo
import me.kjgleh.myshop.order.command.service.dto.OrderInfo
import javax.persistence.*

@Entity(name = "purchase_order")
class Order(
    @EmbeddedId
    val orderNo: OrderNo,

    @Embedded
    val orderer: Orderer,

    @Embedded
    val shippingInfo: ShippingInfo,

    @ElementCollection
    @CollectionTable(
        name = "order_line",
        joinColumns = [JoinColumn(name = "order_number")]
    )
    @OrderColumn(name = "line_idx")
    val orderLines: List<OrderLine>
) {

    companion object {
        fun of(
            orderInfo: OrderInfo,
            memberInfo: MemberInfo
        ): Order {
            return Order(
                orderNo = orderInfo.orderNo,
                orderer = Orderer(
                    memberId = memberInfo.id,
                    name = memberInfo.name
                ),
                shippingInfo = ShippingInfo(
                    address = Address(
                        zipCode = orderInfo.orderRequest.shippingInfo.address.zipCode,
                        address1 = orderInfo.orderRequest.shippingInfo.address.address1,
                        address2 = orderInfo.orderRequest.shippingInfo.address.address2
                    ),
                    receiver = Receiver(
                        name = orderInfo.orderRequest.shippingInfo.receiver.name,
                        phone = orderInfo.orderRequest.shippingInfo.receiver.phone
                    ),
                    message = orderInfo.orderRequest.shippingInfo.message
                ),
                orderLines = orderInfo.orderLines
            )
        }

        private val equalsAndHashCodeProperties = arrayOf(Order::orderNo)
        private val toStringProperties = arrayOf(
            Order::orderNo
        )
    }

    override fun equals(other: Any?) =
        kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun hashCode() =
        kotlinHashCode(properties = equalsAndHashCodeProperties)
}