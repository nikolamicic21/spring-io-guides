package io.mickeckemi21.springioguides.cachingdata.repository

import io.mickeckemi21.springioguides.cachingdata.model.Book

interface BookRepository {

    fun getByIsbn(isbn: String): Book

}