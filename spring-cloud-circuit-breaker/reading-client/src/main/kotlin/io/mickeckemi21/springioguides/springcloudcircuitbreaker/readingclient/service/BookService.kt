package io.mickeckemi21.springioguides.springcloudcircuitbreaker.readingclient.service

import org.slf4j.LoggerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.ConfigBuilder
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker

import org.springframework.web.reactive.function.client.WebClient

@Service
class BookService(circuitBreakerFactory: ReactiveResilience4JCircuitBreakerFactory) {

    private var webClient: WebClient =
        WebClient
            .builder()
            .baseUrl("http://localhost:8090")
            .build()
    private var readingListCircuitBreaker: ReactiveCircuitBreaker =
        circuitBreakerFactory
            .create("recommended")

    companion object {
        private val log = LoggerFactory.getLogger(BookService::class.java)
    }

    fun readingList(): Mono<String> {
        return readingListCircuitBreaker.run(
            webClient.get()
                .uri("/recommended")
                .retrieve()
                .bodyToMono(String::class.java)
        ) { throwable ->
            log.warn("Error making request to book service", throwable)
            Mono.just("Cloud Native Java (O'Reilly)")
        }
    }

}