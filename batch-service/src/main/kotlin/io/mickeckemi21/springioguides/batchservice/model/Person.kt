package io.mickeckemi21.springioguides.batchservice.model

import kotlin.properties.Delegates

class Person {

    var lastName by Delegates.notNull<String>()
    var firstName by Delegates.notNull<String>()

    override fun toString(): String {
        return "Person(lastName='$lastName', firstName='$firstName')"
    }

}
