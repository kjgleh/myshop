package me.kjgleh.myshop.study

import org.junit.jupiter.api.Test

internal class CoroutineTest {

    @Test
    fun `sync`() {
        // Arrange
        val sut = Coroutine()

        // Act
        sut.sync()

        // Assert
    }

    @Test
    fun `async`() {
        // Arrange
        val sut = Coroutine()

        // Act
        sut.test()

        // Assert
    }
}
