package io.mickeckemi21.springioguides.springcloudcircuitbreaker.bookstoreserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudCircuitBreakerApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudCircuitBreakerApplication>(*args)
}
