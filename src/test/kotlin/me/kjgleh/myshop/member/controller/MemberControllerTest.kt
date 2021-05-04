package me.kjgleh.myshop.member.controller

import com.appmattus.kotlinfixture.kotlinFixture
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.kjgleh.myshop.member.service.FindMemberService
import me.kjgleh.myshop.member.service.UpdateMemberService
import me.kjgleh.myshop.member.service.dto.MemberView
import me.kjgleh.myshop.member.service.dto.UpdateMemberRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [MemberController::class])
internal class MemberControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var findMemberService: FindMemberService

    @MockBean
    private lateinit var updateMemberService: UpdateMemberService

    private val fixture = kotlinFixture()

    @Test
    fun `me returns 200`() {
        // Arrange
        val authorization = fixture<String>()
        val expectedResponseBody = fixture<MemberView>()

        Mockito
            .`when`(findMemberService.findMember(authorization))
            .thenReturn(expectedResponseBody)

        // Act
        val actual = mockMvc.perform(
            get("/api/me")
                .header("Authorization", authorization)
                .contentType(MediaType.APPLICATION_JSON)
        )

        // Assert
        Mockito.verify(findMemberService).findMember(authorization)
        actual.andExpect(status().isOk)
        val actualResponseBody = actual.andReturn().response.contentAsString
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
            jacksonObjectMapper().writeValueAsString(expectedResponseBody)
        )
    }

    @Test
    fun `updateMember returns 200`() {
        // Arrange
        val updateMemberRequest = fixture<UpdateMemberRequest>()

        // Act
        val actual = mockMvc.perform(
            put("/api/me")
                .contentType("application/json")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        updateMemberRequest
                    )
                )
        )

        // Assert
        Mockito.verify(updateMemberService).updateMember(updateMemberRequest)
        actual.andExpect(status().isNoContent)
    }

    @Test
    fun `updateMember returns 500, if updateMemberService throws exception`() {
        // Arrange
        val updateMemberRequest = fixture<UpdateMemberRequest>()
        Mockito
            .`when`(updateMemberService.updateMember(updateMemberRequest))
            .thenThrow(RuntimeException())

        // Act
        val actual = mockMvc.perform(
            put("/api/me")
                .contentType("application/json")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        updateMemberRequest
                    )
                )
        )

        // Assert
        actual.andExpect(status().is5xxServerError)
    }
}
