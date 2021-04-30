package me.kjgleh.myshop.member.service.dto

import me.kjgleh.myshop.member.domain.MemberId

data class UpdateMemberRequest(
    val memberId: MemberId,
    val name: String
)
