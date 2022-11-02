package me.kjgleh.myshop.study

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class Coroutine {

    fun sync() {
        runBlocking {
            val time = measureTimeMillis {
                val one = doSomethingOne()
                val two = doSomethingTwo()
                println("${one + two}")
            }
            println("completed in $time")
        }
    }

    fun async() {
        runBlocking {
            val time = measureTimeMillis {
                val one = async { doSomethingOne() }
                val two = async { doSomethingTwo() }
                println("${one.await() + two.await()}")
            }
            println("completed in $time")
        }
    }

    fun test() {
        runBlocking {
            launch {
                delay(1000L)
                println("World!")
            }
            println("Hello,")
        }
    }

    suspend fun doSomethingOne(): Int {
        delay(1000)
        return 13
    }

    suspend fun doSomethingTwo(): Int {
        delay(1000)
        return 29
    }
}
