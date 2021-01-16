package io.mickeckemi21.springioguides.batchservice.config

import io.mickeckemi21.springioguides.batchservice.listener.JobCompletionNotificationListener
import io.mickeckemi21.springioguides.batchservice.model.Person
import io.mickeckemi21.springioguides.batchservice.processor.PersonItemProcessor
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
class BatchConfig {

    @Bean
    fun reader(): FlatFileItemReader<Person> = FlatFileItemReaderBuilder<Person>()
        .name("personItemReader")
        .resource(ClassPathResource("sample-data.csv"))
        .delimited()
        .names("firstName", "lastName")
        .fieldSetMapper(BeanWrapperFieldSetMapper<Person>().apply {
            setTargetType(Person::class.java)
        })
        .build()

    @Bean
    fun processor(): ItemProcessor<Person, Person> = PersonItemProcessor()

    @Bean
    fun writer(dataSource: DataSource): JdbcBatchItemWriter<Person> = JdbcBatchItemWriterBuilder<Person>()
        .itemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
        .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
        .dataSource(dataSource)
        .build()

    @Bean
    fun importUserJob(
        jobBuilderFactory: JobBuilderFactory,
        listener: JobCompletionNotificationListener,
        step: Step
    ): Job = jobBuilderFactory
            .get("importUserJob")
            .incrementer(RunIdIncrementer())
            .listener(listener)
            .flow(step)
            .end()
            .build()

    @Bean
    fun step(
        stepBuilderFactory: StepBuilderFactory,
        reader: FlatFileItemReader<Person>,
        processor: ItemProcessor<Person, Person>,
        writer: JdbcBatchItemWriter<Person>,
    ): Step = stepBuilderFactory
        .get("step")
        .chunk<Person, Person>(10)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build()

}
