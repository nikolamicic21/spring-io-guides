package io.mickeckemi21.springioguides.batchservice.listener

import io.mickeckemi21.springioguides.batchservice.model.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

@Component
class JobCompletionNotificationListener(private val jdbcTemplate: JdbcTemplate)
    : JobExecutionListenerSupport() {

    companion object {
        val log: Logger = LoggerFactory.getLogger(JobCompletionNotificationListener::class.java)
    }

    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.status == BatchStatus.COMPLETED) {
            log.info("JOB FINISHED! Time to verify results")
            jdbcTemplate.query("SELECT first_name, last_name FROM people") { rs, _ ->
                Person().apply {
                    firstName = rs.getString(1)
                    lastName = rs.getString(2)
                }
            }.forEach { log.info("Found <$it> in the database") }
        }
    }

}
