package io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.converter.KotlinSerializationJsonMessageConverter
import org.springframework.messaging.converter.MessageConverter

@SpringBootApplication
class UsageCostLoggerApplication

fun main(args: Array<String>) {
    runApplication<UsageCostLoggerApplication>(*args)
}
