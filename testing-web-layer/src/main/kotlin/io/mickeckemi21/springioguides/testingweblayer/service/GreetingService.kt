package io.mickeckemi21.springioguides.testingweblayer.service

import org.springframework.stereotype.Service

@Service
class GreetingService {

    fun greet(): String = "Hello, World"

}