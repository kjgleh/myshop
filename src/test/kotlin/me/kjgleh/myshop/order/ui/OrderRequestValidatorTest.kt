package me.kjgleh.myshop.order.ui

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.order.command.dto.OrderRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class OrderRequestValidatorTest {

    companion object {
        private val kotlinFixture = kotlinFixture()
    }

    @Test
    fun `given orderProduct is empty when order request validate then throw exception`() {
        // Act && Assert
        val sut = OrderRequestValidator
        assertThrows<IllegalArgumentException> {
            sut.validate(
                kotlinFixture<OrderRequest>().copy(
                    orderProducts = listOf()
                )
            )
        }
    }
}