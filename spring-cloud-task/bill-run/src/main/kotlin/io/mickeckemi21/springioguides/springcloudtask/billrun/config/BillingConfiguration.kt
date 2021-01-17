package io.mickeckemi21.springioguides.springcloudtask.billrun.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.mickeckemi21.springioguides.springcloudtask.billrun.model.Bill
import io.mickeckemi21.springioguides.springcloudtask.billrun.model.Usage
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.json.JacksonJsonObjectReader
import org.springframework.batch.item.json.JsonObjectReader
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.task.configuration.EnableTask
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
@EnableTask
class BillingConfiguration {

    @Bean
    fun billStep(
        stepBuilderFactory: StepBuilderFactory,
        usageJsonItemReader: ItemReader<Usage>,
        usageBillItemProcessor: ItemProcessor<Usage, Bill>,
        jdbcBillItemWriter: ItemWriter<Bill>
    ): Step = stepBuilderFactory
        .get("billProcessing")
        .chunk<Usage, Bill>(1)
        .reader(usageJsonItemReader)
        .processor(usageBillItemProcessor)
        .writer(jdbcBillItemWriter)
        .build()

    @Bean
    fun billJob(
        jobBuilderFactory: JobBuilderFactory,
        billStep: Step
    ): Job = jobBuilderFactory
        .get("billJob")
        .incrementer(RunIdIncrementer())
        .start(billStep)
        .build()

    @Bean
    fun usageJsonItemReader(
        @Value("\${usage.file.path:classpath:usageinfo.json}") usageResource: Resource
    ): ItemReader<Usage> {
        val objectMapper = ObjectMapper()
        val jsonObjectReader = JacksonJsonObjectReader(Usage::class.java)
        jsonObjectReader.setMapper(objectMapper)

        return JsonItemReaderBuilder<Usage>()
            .name("usageJsonItemReader")
            .jsonObjectReader(jsonObjectReader)
            .resource(usageResource)
            .build()
    }

    @Bean
    fun jdbcBillWriter(dataSource: DataSource): ItemWriter<Bill> =
        JdbcBatchItemWriterBuilder<Bill>()
            .beanMapped()
            .dataSource(dataSource)
            .sql(
                "INSERT INTO BILL_STATEMENTS " +
                        "(id, first_name, last_name, minutes, data_usage, bill_amount) VALUES " +
                        "(:id, :firstName, :lastName, :minutes, :dataUsage, :billAmount)"
            )
            .build()

}
