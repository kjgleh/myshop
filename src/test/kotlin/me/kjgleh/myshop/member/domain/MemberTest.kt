package me.kjgleh.myshop.member.domain

import com.appmattus.kotlinfixture.kotlinFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberTest {

    companion object {
        private val fixture = kotlinFixture()
    }

    @Test
    fun `update member correctly`() {
        // Arrange
        val member = fixture<Member>()
        val name = fixture<String>()

        // Act
        member.update(name)

        // Arrange
        assertThat(member.name).isEqualTo(name)
    }
}
