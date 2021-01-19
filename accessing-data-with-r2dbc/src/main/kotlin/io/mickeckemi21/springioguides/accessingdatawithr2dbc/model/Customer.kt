package io.mickeckemi21.springioguides.accessingdatawithr2dbc.model

import org.springframework.data.annotation.Id

class Customer {

    @Id
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null

    constructor(firstName: String?, lastName: String?) {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "Customer(id=$id, firstName=$firstName, lastName=$lastName)"
    }

}