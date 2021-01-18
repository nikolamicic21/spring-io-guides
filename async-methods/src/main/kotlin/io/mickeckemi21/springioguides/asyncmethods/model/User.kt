package io.mickeckemi21.springioguides.asyncmethods.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.properties.Delegates

@JsonIgnoreProperties(ignoreUnknown=true)
class User {

    var name by Delegates.notNull<String>()
    var blog by Delegates.notNull<String>()

    override fun toString(): String =
        "User: name=$name, blog=$blog"
}