package me.kjgleh.yes25.order.controller

import me.kjgleh.yes25.member.domain.MemberId
import me.kjgleh.yes25.member.repository.MemberRepository
import me.kjgleh.yes25.order.command.domain.OrderNo
import me.kjgleh.yes25.order.command.dto.OrderRequest
import me.kjgleh.yes25.order.command.service.PlaceOrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val placeOrderService: PlaceOrderService,
    private val memberRepository: MemberRepository
) {

    @PostMapping
    fun order(@RequestBody orderRequest: OrderRequest) {
        val orderNo = OrderNo.nextOrderNo()
        val member =
            memberRepository.findById(MemberId(orderRequest.orderer.memberId))
                .orElseThrow {
                    IllegalArgumentException("This member id does not exist.")
                }
        placeOrderService.placeOrder(orderNo, orderRequest, member.id, member.name)
    }
}