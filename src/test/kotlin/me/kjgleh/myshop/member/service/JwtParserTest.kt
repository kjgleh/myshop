package me.kjgleh.myshop.member.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JwtParserTest {

    @Test
    fun `getMemberId`() {
        // Arrange
        val authorization =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImEwOTg0ZTc1LTJiZmMtNDQ5Zi1iMGY2LTlmMWQzMDgxZDRjMiJ9.F8vtwJxtEJClVIMT-CGg8RBHfm6FeoSDm6S7o9gOAYA"

        // Act
        val actual = JwtParser.getMemberId(authorization)

        // Assert
        assertThat(actual).isEqualTo("a0984e75-2bfc-449f-b0f6-9f1d3081d4c2")
    }
}