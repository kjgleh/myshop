package me.kjgleh.myshop.member.service

import me.kjgleh.myshop.member.domain.MemberId
import me.kjgleh.myshop.member.exception.MemberNotFoundException
import me.kjgleh.myshop.member.repository.MemberRepository
import me.kjgleh.myshop.member.service.dto.MemberView
import org.springframework.stereotype.Service

@Service
class FindMemberService(
    private val memberRepository: MemberRepository
) {

    fun findMember(authorization: String): MemberView {
        val member =
            memberRepository.findByMemberId(
                MemberId(
                    JwtParser.getMemberId(
                        authorization
                    )
                )
            ).orElseThrow {
                MemberNotFoundException()
            }
        return MemberView(
            id = member.memberId.id,
            name = member.name
        )
    }
}