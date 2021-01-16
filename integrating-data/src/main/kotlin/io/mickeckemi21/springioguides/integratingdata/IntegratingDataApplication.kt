package io.mickeckemi21.springioguides.integratingdata

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
@ImportResource("/integration/integration.xml")
class IntegratingDataApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(IntegratingDataApplication::class.java)
    val context = runApplication<IntegratingDataApplication>(*args)
    log.info("Hit Enter to terminate")
    System.`in`.read()
    context.close()
}
