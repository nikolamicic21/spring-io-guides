package io.mickeckemi21.springioguides.multimoduleproject.library.service

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
@EnableConfigurationProperties(ServiceProperties::class)
class MyService(private val serviceProperties: ServiceProperties) {

    fun message(): String = serviceProperties.message

}