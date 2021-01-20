package io.mickeckemi21.springioguides.testingweblayer.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.boot.web.server.LocalServerPort
import kotlin.properties.Delegates


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class HomeControllerIT {

    @LocalServerPort
    private var port: Int = 8080

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun greetingShouldReturnDefaultMessage() {
        val expectedResponse = "Hello, World"
        val actualResponse = restTemplate.getForObject(
            "http://localhost:$port/",
            String::class.java
        )

        assertEquals(expectedResponse, actualResponse)
    }

}