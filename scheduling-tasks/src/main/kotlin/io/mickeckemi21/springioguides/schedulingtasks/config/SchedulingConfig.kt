package io.mickeckemi21.springioguides.schedulingtasks.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration(proxyBeanMethods = false)
@EnableScheduling
class SchedulingConfig
