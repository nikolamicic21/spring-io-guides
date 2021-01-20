package io.mickeckemi21.springioguides.testingweblayer

import io.mickeckemi21.springioguides.testingweblayer.controller.HomeController
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestingWebLayerApplicationTests {

	@Autowired
	private lateinit var homeController: HomeController

	@Test
	fun contextLoads() {
		assertNotNull(homeController)
	}

}
