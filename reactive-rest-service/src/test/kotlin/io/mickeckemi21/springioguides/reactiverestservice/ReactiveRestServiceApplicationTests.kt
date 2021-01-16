package io.mickeckemi21.springioguides.reactiverestservice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

@ExtendWith(SpringExtension::class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
internal class ReactiveRestServiceApplicationTests {

	@Autowired
	private lateinit var webTestClient: WebTestClient

	@Test
	internal fun `when get hello - then response equals to return value of greeting handler`() {
		val expected: String = "Hello from the reactive ReST!"
		val returnResult = webTestClient.get()
			.uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchange()
			.expectStatus().isOk
			.expectBody(String::class.java)
			.returnResult()

		assertEquals(expected, returnResult.responseBody!!)
	}

}
