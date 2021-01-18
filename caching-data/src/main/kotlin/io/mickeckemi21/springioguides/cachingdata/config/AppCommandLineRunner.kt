package io.mickeckemi21.springioguides.cachingdata.config

import io.mickeckemi21.springioguides.cachingdata.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AppCommandLineRunner(private val bookRepository: BookRepository)
    : CommandLineRunner {

    companion object {
        private val log = LoggerFactory.getLogger(AppCommandLineRunner::class.java)
    }

    override fun run(vararg args: String?) {
        log.info(".... Fetching books")
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
    }
}