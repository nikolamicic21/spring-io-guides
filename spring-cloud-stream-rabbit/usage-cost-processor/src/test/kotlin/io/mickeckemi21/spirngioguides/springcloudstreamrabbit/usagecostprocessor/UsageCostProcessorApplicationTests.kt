package io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.TimeUnit

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class UsageCostProcessorApplicationTests {

    @Autowired
    private lateinit var messageCollector: MessageCollector

    @Autowired
    private lateinit var processor: Processor

    @Test
    fun `test usage cost processor`() {
        // given
        val expectedMessagePayload = """{"userId":"user3","dataCost":25.1,"callCost":10.100000000000001}"""

        // when
        processor.input().send(
            MessageBuilder
                .withPayload("""{"userId":"user3","duration":101,"data":502}""")
                .build()
        )

        val message = messageCollector.forChannel(processor.output())
            .poll(1, TimeUnit.SECONDS)

        // then
        assertEquals(expectedMessagePayload, message.payload)
    }

}
