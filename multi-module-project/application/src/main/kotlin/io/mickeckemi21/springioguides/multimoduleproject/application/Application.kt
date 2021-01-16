package io.mickeckemi21.springioguides.multimoduleproject.application

import io.mickeckemi21.springioguides.multimoduleproject.library.service.MyService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(scanBasePackages = ["io.mickeckemi21.springioguides.multimoduleproject"])
@RestController
open class Application(private val service: MyService) {
    @GetMapping("/home")
    fun home(): String = service.message()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}