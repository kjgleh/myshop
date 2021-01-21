package me.kjgleh.yes25.order.command.domain

import me.kjgleh.yes25.order.command.dto.OrderRequest
import javax.persistence.*

@Entity
@Table(name = "purchase_order")
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
            orderNo: OrderNo,
            orderRequest: OrderRequest,
            memberName: String,
            orderLines: List<OrderLine>
        ): Order {
            return Order(
                orderNo = orderNo,
                orderer = Orderer(
                    memberId = orderRequest.orderer.memberId,
                    name = memberName
                ),
                shippingInfo = ShippingInfo(
                    address = Address(
                        zipCode = orderRequest.shippingInfo.address.zipCode,
                        address1 = orderRequest.shippingInfo.address.address1,
                        address2 = orderRequest.shippingInfo.address.address2
                    ),
                    receiver = Receiver(
                        name = orderRequest.shippingInfo.receiver.name,
                        phone = orderRequest.shippingInfo.receiver.phone
                    ),
                    message = orderRequest.shippingInfo.message
                ),
                orderLines = orderLines
            )
        }
    }
}