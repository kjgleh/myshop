package me.kjgleh.myshop.common.jpa

import me.kjgleh.myshop.common.model.Email
import me.kjgleh.myshop.common.model.EmailSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class EmailSetConverterTest {

    private lateinit var emailSetConverter: EmailSetConverter

    @BeforeEach
    fun setUp() {
        emailSetConverter = EmailSetConverter()
    }

    @Test
    fun `convert to database column correctly`() {
        // Arrange
        val firstEmail = Email(address = UUID.randomUUID().toString())
        val secondEmail = Email(address = UUID.randomUUID().toString())
        val thirdEmail = Email(address = UUID.randomUUID().toString())
        val emailSet = EmailSet(
            emails = hashSetOf(
                firstEmail, secondEmail, thirdEmail
            )
        )

        // Act
        val sut = emailSetConverter
        val actual = sut.convertToDatabaseColumn(emailSet)

        // Assert
        assertThat(actual).contains(
            firstEmail.address,
            secondEmail.address,
            thirdEmail.address
        )
    }

    @Test
    fun `convert to entity attribute correctly`() {
        // Arrange
        val firstEmail = Email(address = UUID.randomUUID().toString())
        val secondEmail = Email(address = UUID.randomUUID().toString())
        val thirdEmail = Email(address = UUID.randomUUID().toString())
        val dbData = "$firstEmail,$secondEmail,$thirdEmail"

        // Act
        val sut = emailSetConverter
        val actual = sut.convertToEntityAttribute(dbData)

        // Assert
        assertThat(actual.emails.size).isEqualTo(3)
    }
}