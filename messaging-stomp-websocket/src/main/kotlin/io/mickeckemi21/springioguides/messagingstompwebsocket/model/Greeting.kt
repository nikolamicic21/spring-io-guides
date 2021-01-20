package io.mickeckemi21.springioguides.messagingstompwebsocket.model

import kotlin.properties.Delegates

class Greeting {

    var content by Delegates.notNull<String>()

}