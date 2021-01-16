package io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger.sink

import io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger.model.UsageCostDetail
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@EnableBinding(Sink::class)
class UsageCostLogger {

    private val log = LoggerFactory.getLogger(UsageCostLogger::class.java)

    @StreamListener(Sink.INPUT)
    fun logUserCost(@Payload usageCostDetail: UsageCostDetail) {
        log.info("received: $usageCostDetail")
    }

}
