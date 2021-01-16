package io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger

import com.nhaarman.mockitokotlin2.capture
import io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger.model.UsageCostDetail
import io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger.sink.UsageCostLogger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.eq
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class, MockitoExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsageCostLoggerApplicationTests {

    @SpyBean
    private lateinit var usageCostLogger: UsageCostLogger

    @Captor
    private lateinit var usageCostDetailArgCaptor: ArgumentCaptor<UsageCostDetail>

    @Autowired
    private lateinit var sink: Sink

    @Test
    fun `test usage cost logger`() {
        // given
        val expectedUsageCostDetail = UsageCostDetail().apply {
            userId = "user3"
            callCost = 10.100000000000001
            dataCost = 25.1
        }

        doNothing().`when`(usageCostLogger).logUserCost(capture(usageCostDetailArgCaptor))

        // when
        sink.input().send(
            MessageBuilder
                .withPayload("""{"userId":"user3","callCost":10.100000000000001,"dataCost":25.1}""")
                .build()
        )

        // then
        val actualUsageCostDetail = usageCostDetailArgCaptor.value

        assertEquals(expectedUsageCostDetail, actualUsageCostDetail)
        verify(usageCostLogger).logUserCost(expectedUsageCostDetail)
    }

    @EnableAutoConfiguration
    @EnableBinding(Sink::class)
    class TestConfig {

        @Bean
        @Primary
        fun spyUsageCostLogger(): UsageCostLogger = spy(UsageCostLogger())

    }

}
