package io.mickeckemi21.springioguides.usagedetailssender

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.TimeUnit

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class UsageDetailsSenderApplicationTests {

	@Autowired
	private lateinit var messageCollector: MessageCollector

	@Autowired
	private lateinit var source: Source

	@Test
	fun contextLoads() {
	}

	@Test
	fun `test usage details sender`() {
		val message = messageCollector.forChannel(source.output())
			.poll(1L, TimeUnit.SECONDS)
		val jsonMessage = message.payload.toString()

		assertTrue(jsonMessage.contains("userId"))
		assertTrue(jsonMessage.contains("duration"))
		assertTrue(jsonMessage.contains("data"))
	}

}
