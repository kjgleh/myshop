package me.kjgleh.myshop.order.ui

import io.swagger.annotations.Api
import me.kjgleh.myshop.member.domain.MemberId
import me.kjgleh.myshop.member.repository.MemberRepository
import me.kjgleh.myshop.order.command.dto.OrderRequest
import me.kjgleh.myshop.order.command.service.PlaceOrderService
import me.kjgleh.myshop.order.command.service.dto.MemberInfo
import me.kjgleh.myshop.order.command.service.dto.OrderResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["Order"], description = " ")
class OrderController(
    private val placeOrderService: PlaceOrderService,
    private val memberRepository: MemberRepository
) {

    @PostMapping("/api/orders/order")
    fun order(@RequestBody orderRequest: OrderRequest): ResponseEntity<OrderResponse> {
        OrderRequestValidator.validate(orderRequest)

        val member =
            memberRepository.findById(MemberId(orderRequest.orderer.memberId))
                .orElseThrow {
                    IllegalArgumentException("This member id does not exist.")
                }

        val orderResponse = placeOrderService.placeOrder(
            orderRequest,
            MemberInfo(member.id, member.name)
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse)
    }
}