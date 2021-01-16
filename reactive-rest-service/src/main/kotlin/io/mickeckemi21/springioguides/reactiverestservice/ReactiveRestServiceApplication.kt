package io.mickeckemi21.springioguides.reactiverestservice

import io.mickeckemi21.springioguides.reactiverestservice.client.GreetingClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveRestServiceApplication

fun main(args: Array<String>) {
	runApplication<ReactiveRestServiceApplication>(*args)

	val client = GreetingClient()
	println(client.getResult())
}
