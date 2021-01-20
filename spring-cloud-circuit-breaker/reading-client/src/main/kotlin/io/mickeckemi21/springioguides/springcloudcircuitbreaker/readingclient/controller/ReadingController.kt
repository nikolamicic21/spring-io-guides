package io.mickeckemi21.springioguides.springcloudcircuitbreaker.readingclient.controller

import io.mickeckemi21.springioguides.springcloudcircuitbreaker.readingclient.service.BookService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

import reactor.core.publisher.Mono

import org.springframework.web.bind.annotation.RequestMapping

@RestController
class ReadingController(private val bookService: BookService) {

    @RequestMapping("/to-read")
    fun toRead(): Mono<String> = bookService.readingList()

}