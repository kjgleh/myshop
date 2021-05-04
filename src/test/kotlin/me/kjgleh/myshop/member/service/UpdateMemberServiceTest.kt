package me.kjgleh.myshop.member.service

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.member.domain.Member
import me.kjgleh.myshop.member.repository.MemberRepository
import me.kjgleh.myshop.member.service.dto.UpdateMemberRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class UpdateMemberServiceTest {

    @Mock
    private lateinit var memberRepository: MemberRepository

    @InjectMocks
    private lateinit var updateMemberService: UpdateMemberService

    private val fixture = kotlinFixture()

    @Test
    fun `updateMember correctly`() {
        // Arrange
        val updateMemberRequest = fixture<UpdateMemberRequest>()
        val member = fixture<Member>()

        Mockito
            .`when`(memberRepository.findByMemberId(updateMemberRequest.memberId))
            .thenReturn(Optional.of(member))

        // Act
        val sut = updateMemberService
        sut.updateMember(updateMemberRequest)

        // Assert
        assertThat(member.name).isEqualTo(updateMemberRequest.name)
    }

    @Test
    fun `updateMember throws IllegalArgumentException, if no member is found`() {
        // Arrange
        val updateMemberRequest = fixture<UpdateMemberRequest>()
        Mockito
            .`when`(memberRepository.findByMemberId(updateMemberRequest.memberId))
            .thenThrow(IllegalArgumentException())

        // Act
        val sut = updateMemberService
        val act = {
            sut.updateMember(updateMemberRequest)
        }

        // Assert
        assertThrows<IllegalArgumentException> { act() }
    }
}
