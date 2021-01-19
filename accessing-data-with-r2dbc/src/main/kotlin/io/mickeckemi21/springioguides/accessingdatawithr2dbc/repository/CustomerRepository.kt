package io.mickeckemi21.springioguides.accessingdatawithr2dbc.repository

import io.mickeckemi21.springioguides.accessingdatawithr2dbc.model.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CustomerRepository : ReactiveCrudRepository<Customer, Long> {

    fun findByLastName(lastName: String): Flux<Customer>

}