package io.mickeckemi21.springioguides.reactiverestservice.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class GreetingHandler {

    fun hello(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromValue("Hello from the reactive ReST!"))

}