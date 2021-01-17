package io.mickeckemi21.springioguides.springcloudtask.billsetup.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.cloud.task.configuration.EnableTask
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
@EnableTask
class TaskConfig {

    companion object {
        private val log = LoggerFactory.getLogger(TaskConfig::class.java)
    }

    @Bean
    fun commandLineRunner(dataSource: DataSource) = CommandLineRunner {
        log.info("Executing CommandLineRunner...")
        JdbcTemplate(dataSource).also { template ->
            template.execute(
                "CREATE TABLE IF NOT EXISTS BILL_STATEMENTS " +
                        "(id int, first_name varchar(50), last_name varchar(50), " +
                        "minutes int, data_usage int, bill_amount double)"
            )
        }
    }

}
