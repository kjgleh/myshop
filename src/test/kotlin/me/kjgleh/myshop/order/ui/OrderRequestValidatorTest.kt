package me.kjgleh.myshop.order.ui

import com.appmattus.kotlinfixture.kotlinFixture
import me.kjgleh.myshop.order.command.dto.OrderRequest
import me.kjgleh.myshop.order.ui.exception.OrderProductEmptyException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class OrderRequestValidatorTest {

    private val fixture = kotlinFixture()

    @Test
    fun `given orderProduct is empty when order request validate then throw OrderProductEmptyException`() {
        // Act
        val sut = OrderRequestValidator
        val act = {
            sut.validate(
                fixture {
                    property(OrderRequest::orderProducts) { listOf() }
                }
            )
        }

        // Assert
        assertThrows<OrderProductEmptyException> {
            act()
        }
    }
}
