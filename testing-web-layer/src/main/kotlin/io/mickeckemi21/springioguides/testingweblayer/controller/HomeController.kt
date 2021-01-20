package io.mickeckemi21.springioguides.testingweblayer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    @RequestMapping("/")
    @ResponseBody
    fun greeting(): String = "Hello, World"

}