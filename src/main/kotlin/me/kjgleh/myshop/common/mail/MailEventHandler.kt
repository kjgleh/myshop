package me.kjgleh.myshop.common.mail

import me.kjgleh.myshop.order.command.service.OrderedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MailEventHandler {

    @Async
    @EventListener
    fun sendMail(orderedEvent: OrderedEvent) {
        Thread.sleep(5000)
        println("send mail...")
    }
}
