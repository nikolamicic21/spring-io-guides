package io.mickeckemi21.springioguides.asyncmethods.service

import io.mickeckemi21.springioguides.asyncmethods.model.User
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class GitHubLookupService(private val restTemplateBuilder: RestTemplateBuilder) {

    companion object {
        private val log = LoggerFactory.getLogger(GitHubLookupService::class.java)
    }

    private var restTemplate: RestTemplate = restTemplateBuilder.build()

    @Async("taskExecutor")
    fun findUser(user: String): CompletableFuture<User> {
        log.info("lookup user: $user")
        val url = "https://api.github.com/users/$user"
        val receivedUser = restTemplate.getForObject(url, User::class.java)
        Thread.sleep(1000L)

        return CompletableFuture.completedFuture(receivedUser)
    }

}