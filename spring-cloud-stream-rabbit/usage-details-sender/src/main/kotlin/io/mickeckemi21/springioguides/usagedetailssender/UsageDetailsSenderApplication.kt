package io.mickeckemi21.springioguides.usagedetailssender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class UsageDetailsSenderApplication

fun main(args: Array<String>) {
	runApplication<UsageDetailsSenderApplication>(*args)
}
