package io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class UsageCostProcessorApplication

fun main(args: Array<String>) {
    runApplication<UsageCostProcessorApplication>(*args)
}
