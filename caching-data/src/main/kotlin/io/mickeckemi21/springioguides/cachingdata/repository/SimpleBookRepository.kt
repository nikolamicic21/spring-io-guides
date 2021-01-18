package io.mickeckemi21.springioguides.cachingdata.repository

import io.mickeckemi21.springioguides.cachingdata.model.Book
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class SimpleBookRepository : BookRepository {

    @Cacheable("books")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()
        return Book(isbn, "Some book")
    }

    private fun simulateSlowService() {
        try {
            val time = 3000L
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }

}