package io.mickeckemi21.springioguides.integratingdata

import com.rometools.rome.feed.synd.SyndEntryImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.integration.endpoint.SourcePollingChannelAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@SpringBootTest(
    properties = [
        "feed.auto-startup=false",
        "feed.file.name=Test"
    ]
)
class IntegratingDataApplicationTests {

    @Autowired
    private lateinit var newsAdapter: SourcePollingChannelAdapter

    @Autowired
    private lateinit var news: MessageChannel

    @Test
    fun `integration flow feed - file test`() {
        assertFalse(newsAdapter.isRunning)

        val testTitle = "Test Title"
        val testLink = "http://characters/frodo"
        val syndEntry = SyndEntryImpl().apply {
            title = testTitle
            link = testLink
        }

        val fileOut = File("tmp/si/Test")
        fileOut.delete()

        assertFalse(fileOut.exists())

        news.send(MessageBuilder.withPayload(syndEntry).build())

        assertTrue(fileOut.exists())

        val reader = BufferedReader(FileReader(fileOut))
        val line = reader.readLine()

        assertEquals("$testTitle @ $testLink", line)

        reader.close()
        fileOut.delete()
    }

}
