package io.mickeckemi21.springioguides.messagingstompwebsocket.controller

import io.mickeckemi21.springioguides.messagingstompwebsocket.model.Greeting
import io.mickeckemi21.springioguides.messagingstompwebsocket.model.HelloMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000)
        return Greeting().apply {
            content = "Hello, ${HtmlUtils.htmlEscape(message.name)}!"
        }
    }

}