package io.mickeckemi21.springioguides.reactiverestservice.client

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

class GreetingClient {

    private val client = WebClient.create("http://localhost:8080")

    fun getResult(): String {
        val responseSpec = client.get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()

        return ">> result: ${responseSpec.bodyToMono(String::class.java).block()!!}"
    }

}