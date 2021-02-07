package me.kjgleh.myshop.member.repository

import me.kjgleh.myshop.member.domain.Member
import me.kjgleh.myshop.member.domain.MemberId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByMemberId(memberId: MemberId): Optional<Member>
}