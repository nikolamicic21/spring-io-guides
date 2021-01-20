package io.mickeckemi21.springioguides.springcloudcircuitbreaker.bookstoreserver.controller

import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

import org.springframework.web.bind.annotation.RequestMapping

@RestController
class BookstoreController {

    @RequestMapping("/recommended")
    fun readingList(): Mono<String> =
        Mono.just(
            "Spring in Action (Manning), " +
                    "Cloud Native Java (O'Reilly), " +
                    "Learning Spring Boot (Packt)")

}