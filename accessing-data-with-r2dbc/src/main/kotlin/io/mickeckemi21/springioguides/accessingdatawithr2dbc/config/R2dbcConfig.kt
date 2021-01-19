package io.mickeckemi21.springioguides.accessingdatawithr2dbc.config

import io.mickeckemi21.springioguides.accessingdatawithr2dbc.model.Customer
import io.mickeckemi21.springioguides.accessingdatawithr2dbc.repository.CustomerRepository
import io.r2dbc.spi.ConnectionFactory
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import java.time.Duration

@Configuration
class R2dbcConfig {

    companion object {
        private val log = LoggerFactory.getLogger(R2dbcConfig::class.java)
    }

    @Bean
    fun connectionFactoryInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        }

    @Bean
    fun commandLineRunner(customerRepository: CustomerRepository): CommandLineRunner =
        CommandLineRunner {
            // save a few customers
            customerRepository.saveAll(
                listOf(
                    Customer("Jack", "Bauer"),
                    Customer("Chloe", "O'Brian"),
                    Customer("Kim", "Bauer"),
                    Customer("David", "Palmer"),
                    Customer("Michelle", "Dessler")
                )
            ).blockLast(Duration.ofSeconds(10))

            // fetch all customers
            log.info("Customers found with findAll():")
            log.info("-------------------------------")
            customerRepository.findAll().doOnNext{ customer ->
                log.info(customer.toString())
            }.blockLast(Duration.ofSeconds(10))

            log.info("")

            // fetch an individual customer by ID
            customerRepository.findById(1L).doOnNext{ customer ->
                log.info("Customer found with findById(1L):")
                log.info("--------------------------------")
                log.info(customer.toString())
                log.info("")
            }.block(Duration.ofSeconds(10))


            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):")
            log.info("--------------------------------------------")
            customerRepository.findByLastName("Bauer").doOnNext{ bauer ->
                log.info(bauer.toString())
            }.blockLast(Duration.ofSeconds(10))
            log.info("")
        }

}