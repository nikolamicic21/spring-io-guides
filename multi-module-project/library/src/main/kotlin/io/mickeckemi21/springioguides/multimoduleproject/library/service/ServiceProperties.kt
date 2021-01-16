package io.mickeckemi21.springioguides.multimoduleproject.library.service

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("service")
class ServiceProperties {

    lateinit var message: String

}