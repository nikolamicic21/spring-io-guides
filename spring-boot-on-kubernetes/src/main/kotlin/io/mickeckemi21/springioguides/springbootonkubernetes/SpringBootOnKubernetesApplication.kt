package io.mickeckemi21.springioguides.springbootonkubernetes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootOnKubernetesApplication

fun main(args: Array<String>) {
	runApplication<SpringBootOnKubernetesApplication>(*args)
}
