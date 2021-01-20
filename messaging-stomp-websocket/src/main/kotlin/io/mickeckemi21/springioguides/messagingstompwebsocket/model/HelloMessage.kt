package io.mickeckemi21.springioguides.messagingstompwebsocket.model

import kotlin.properties.Delegates

class HelloMessage {

    var name by Delegates.notNull<String>()

}