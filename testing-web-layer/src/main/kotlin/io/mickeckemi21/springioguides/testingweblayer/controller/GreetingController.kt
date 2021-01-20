package io.mickeckemi21.springioguides.testingweblayer.controller

import io.mickeckemi21.springioguides.testingweblayer.service.GreetingService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody

import org.springframework.web.bind.annotation.RequestMapping

@Controller
class GreetingController(private val greetingService: GreetingService) {

    @RequestMapping("/greeting")
    @ResponseBody
    fun greeting(): String = greetingService.greet()

}