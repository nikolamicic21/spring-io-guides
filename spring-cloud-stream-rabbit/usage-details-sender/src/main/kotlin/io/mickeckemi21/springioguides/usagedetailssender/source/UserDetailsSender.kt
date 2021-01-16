package io.mickeckemi21.springioguides.usagedetailssender.source

import io.mickeckemi21.springioguides.usagedetailssender.model.UserDetails
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageBuilder.withPayload
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
@EnableBinding(Source::class)
open class UserDetailsSender
constructor(
    private val source: Source
) {

    private val users = listOf(
        "user1",
        "user2",
        "user3",
        "user4",
        "user5"
    )

    @Scheduled(fixedDelay = 3000L)
    fun sendEvents() {
        val userDetails = UserDetails()
        userDetails.apply {
            userId = users[(0..4).random()]
            duration = (0..300).random().toLong()
            data = (0..700).random().toLong()
        }

        source.output()
            .send(withPayload(userDetails).build())
    }

}