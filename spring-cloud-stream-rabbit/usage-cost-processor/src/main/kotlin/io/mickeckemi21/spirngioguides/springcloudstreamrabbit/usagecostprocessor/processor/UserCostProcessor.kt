package io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor.processor

import io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor.model.UsageCostDetail
import io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor.model.UsageDetail
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
@EnableBinding(Processor::class)
open class UserCostProcessor {

    companion object {
        const val ratePerSecond = 0.1
        const val ratePerMB = 0.05
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    fun processUsageCost(usageDetail: UsageDetail): Message<UsageCostDetail> {
        val usageCostDetail = UsageCostDetail().apply {
            userId = usageDetail.userId
            callCost = usageDetail.duration * ratePerSecond
            dataCost = usageDetail.data * ratePerMB
        }

        return MessageBuilder
            .withPayload(usageCostDetail)
            .build()
    }

}
