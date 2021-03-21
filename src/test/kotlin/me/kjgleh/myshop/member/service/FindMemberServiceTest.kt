package me.kjgleh.myshop.member.service

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.member.domain.Member
import me.kjgleh.myshop.member.domain.MemberId
import me.kjgleh.myshop.member.exception.MemberNotFoundException
import me.kjgleh.myshop.member.repository.MemberRepository
import me.kjgleh.myshop.member.service.dto.MemberView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest(classes = [FindMemberService::class])
internal class FindMemberServiceTest @Autowired constructor(
    private val findMemberService: FindMemberService
) {

    @MockBean
    private lateinit var memberRepository: MemberRepository

    companion object {
        private val fixture = kotlinFixture()
    }

    @Test
    fun `given authorization is invalid when findMember then throw MemberNotFoundException`() {
        // Arrange
        val authorization =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImEwOTg0ZTc1LTJiZmMtNDQ5Zi1iMGY2LTlmMWQzMDgxZDRjMiJ9.F8vtwJxtEJClVIMT-CGg8RBHfm6FeoSDm6S7o9gOAYA"

        mockMemberRepositoryFindByMemberId(
            authorization = authorization,
            returnValue = Optional.empty()
        )

        // Act
        val sut = findMemberService
        val act = {
            sut.findMember(authorization)
        }

        // Assert
        assertThrows<MemberNotFoundException> {
            act()
        }
    }

    private fun mockMemberRepositoryFindByMemberId(
        authorization: String,
        returnValue: Optional<Member>
    ) {
        Mockito
            .`when`(
                memberRepository.findByMemberId(
                    MemberId(
                        JwtParser.getMemberId(
                            authorization
                        )
                    )
                )
            )
            .thenReturn(returnValue)
    }

    @Test
    fun `given authorization is valid when findMember then return MemberView`() {
        // Arrange
        val authorization =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImEwOTg0ZTc1LTJiZmMtNDQ5Zi1iMGY2LTlmMWQzMDgxZDRjMiJ9.F8vtwJxtEJClVIMT-CGg8RBHfm6FeoSDm6S7o9gOAYA"

        val member = fixture<Member>()
        mockMemberRepositoryFindByMemberId(
            authorization = authorization,
            returnValue = Optional.of(member)
        )

        // Act
        val sut = findMemberService
        val actual = sut.findMember(authorization)

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(
            MemberView(
                id = member.memberId.id,
                name = member.name
            )
        )
    }
}