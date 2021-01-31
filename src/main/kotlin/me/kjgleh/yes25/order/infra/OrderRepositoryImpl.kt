package me.kjgleh.yes25.order.infra

import me.kjgleh.yes25.common.infra.QuerydslRepositorySupportCustom
import me.kjgleh.yes25.order.command.domain.Order
import me.kjgleh.yes25.order.command.domain.QOrder.order
import me.kjgleh.yes25.order.infra.dto.OrderView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.support.PageableExecutionUtils
import javax.persistence.EntityManager

class OrderRepositoryImpl(entityManager: EntityManager) : OrderRepositoryCustom,
    QuerydslRepositorySupportCustom(entityManager, Order::class.java) {

    override fun selectByOrderer(ordererId: String, pageable: Pageable): Page<OrderView> {
        val queryFactory = getQueryFactory()
        val jpaQuery = queryFactory
            .select(order.orderer.name)
            .from(order)
            .where(order.orderer.memberId.id.eq(ordererId))

        val content = getContent(pageable) { jpaQuery }.map { OrderView(it) }

        return PageableExecutionUtils.getPage(content, pageable, jpaQuery::fetchCount)
    }

}