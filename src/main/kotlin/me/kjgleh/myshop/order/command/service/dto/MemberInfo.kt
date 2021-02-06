package me.kjgleh.myshop.order.command.service.dto

import me.kjgleh.myshop.member.domain.MemberId

data class MemberInfo(
    val id: MemberId,
    val name: String
)