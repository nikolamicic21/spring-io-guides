package io.mickeckemi21.springioguides.reactiverestservice.router

import io.mickeckemi21.springioguides.reactiverestservice.handler.GreetingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class GreetingRouter {

    @Bean
    fun route(greetingHandler: GreetingHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route(
            RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(TEXT_PLAIN)),
            greetingHandler::hello
        )

}