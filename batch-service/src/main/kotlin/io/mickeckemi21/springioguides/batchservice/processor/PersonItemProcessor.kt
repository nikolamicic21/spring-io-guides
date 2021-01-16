package io.mickeckemi21.springioguides.batchservice.processor

import io.mickeckemi21.springioguides.batchservice.model.Person
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class PersonItemProcessor : ItemProcessor<Person, Person> {

    companion object {
        val log = LoggerFactory.getLogger(PersonItemProcessor::class.java)
    }

    override fun process(person: Person): Person {
        val transformedPerson = Person().apply {
            firstName = person.firstName.toUpperCase()
            lastName = person.lastName.toUpperCase()
        }

        log.info("Converting ($person) into ($transformedPerson)")

        return transformedPerson
    }

}
