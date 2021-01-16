package io.mickeckemi21.springioguides.multimoduleproject.library.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootTest(properties = ["service.message=Hello"])
internal class MyServiceTest {

    @Autowired
    private lateinit var myService: MyService

    @Test
    fun `when message - then value not null`() {
        assertNotNull(myService.message())
    }

    @Test
    fun `when message - then value equals Hello`() {
        assertEquals("Hello", myService.message())
    }

    @SpringBootApplication
    internal open class TestConfiguration

}