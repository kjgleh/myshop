package me.kjgleh.yes25.order.infra

import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kjgleh.yes25.order.command.domain.Order
import me.kjgleh.yes25.order.command.domain.QOrder.order
import me.kjgleh.yes25.order.infra.dto.OrderView
import me.kjgleh.yes25.order.infra.dto.QOrderView
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
        val query: JPQLQuery<OrderView> = querydsl!!.applyPagination(
            pageable,
            queryFactory
                .select(
                    QOrderView(
                        order.orderNo.number,
                        order.orderer.memberId.id,
                        order.orderer.name
                    )
                )
                .from(order)
                .where(order.orderer.memberId.id.eq(ordererId))
        )
        val items = query.fetch()
        val totalCount = query.fetchCount()
        return PageImpl(items, pageable, totalCount)
    }
}

