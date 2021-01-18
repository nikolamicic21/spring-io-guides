package io.mickeckemi21.springioguides.asyncmethods.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig {

    @Bean
    fun taskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 3
            maxPoolSize = 3
        }
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("GithubLookup-")
//        executor.initialize()
        return executor
    }

}