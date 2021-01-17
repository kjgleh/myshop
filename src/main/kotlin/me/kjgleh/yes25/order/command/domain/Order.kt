package me.kjgleh.yes25.order.command.domain

import javax.persistence.*

@Entity
@Table(name = "purchase_order")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderNo: Long = 0L,

    @Embedded
    val orderer: Orderer,

    @Embedded
    val shippingInfo: ShippingInfo,

    @ElementCollection
    @CollectionTable(name = "order_line", joinColumns = [JoinColumn(name = "order_number")])
    @OrderColumn(name = "line_idx")
    val orderLines: List<OrderLine>
)