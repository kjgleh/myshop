package me.kjgleh.myshop.order.infra

import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kjgleh.myshop.order.command.domain.Order
import me.kjgleh.myshop.order.command.domain.QOrder.order
import me.kjgleh.myshop.order.command.domain.QOrderLine.orderLine
import me.kjgleh.myshop.order.infra.dto.OrderLine
import me.kjgleh.myshop.order.infra.dto.OrderView
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class OrderRepositoryImpl(private val queryFactory: JPAQueryFactory) :
    OrderRepositoryCustom, QuerydslRepositorySupport(Order::class.java) {

    override fun selectByOrderer(
        ordererId: String,
        pageable: Pageable
    ): Page<OrderView> {
        val query: JPQLQuery<Order> = querydsl!!.applyPagination(
            pageable,
            queryFactory
                .selectFrom(order)
                .leftJoin(order.orderLines, orderLine).fetchJoin()
                .where(order.orderer.memberId.id.eq(ordererId))
        )

        val orderList = query.fetch()
        val orderViewList = orderList.map {
            OrderView(
                it.orderNo.number,
                it.orderer.memberId.id,
                it.orderer.name,
                it.orderLines.map { orderLine ->
                    OrderLine(
                        productId = orderLine.productId,
                        quantity = orderLine.quantity
                    )
                }
            )
        }

        val totalCount = query.fetchCount()
        return PageImpl(orderViewList, pageable, totalCount)
    }
}

