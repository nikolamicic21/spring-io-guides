package io.mickeckemi21.springioguides.asyncmethods.config

import io.mickeckemi21.springioguides.asyncmethods.service.GitHubLookupService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AppCommandLineRunner(private val gitHubLookupService: GitHubLookupService)
    : CommandLineRunner {

    companion object {
        private val log = LoggerFactory.getLogger(AppCommandLineRunner::class.java)
    }

    override fun run(vararg args: String) {
        val start = System.currentTimeMillis()

        val page1 = gitHubLookupService.findUser("PivotalSoftware")
        val page2 = gitHubLookupService.findUser("CloudFoundry")
        val page3 = gitHubLookupService.findUser("Spring-Projects")

        CompletableFuture.allOf(page1,page2,page3).join()

        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }

}