package me.kjgleh.yes25.order.command.service.dto

import me.kjgleh.yes25.member.domain.MemberId

data class MemberInfo(
    val id: MemberId,
    val name: String
)