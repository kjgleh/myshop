package me.kjgleh.yes25.member.repository

import me.kjgleh.yes25.member.domain.Member
import me.kjgleh.yes25.member.domain.MemberId
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, MemberId>