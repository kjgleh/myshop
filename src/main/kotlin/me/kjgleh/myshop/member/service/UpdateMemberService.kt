package me.kjgleh.myshop.member.service

import me.kjgleh.myshop.member.domain.Member
import me.kjgleh.myshop.member.repository.MemberRepository
import me.kjgleh.myshop.member.service.dto.UpdateMemberRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateMemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun updateMember(updateMemberRequest: UpdateMemberRequest): Member {
        val member =
            memberRepository.findByMemberId(updateMemberRequest.memberId)
                .orElseThrow { IllegalArgumentException("No members(memberID: ${updateMemberRequest.memberId})") }

        member.update(updateMemberRequest.name)

        return member
    }
}
