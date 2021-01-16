package io.mickeckemi21.springioguides.usagedetailssender.model

import kotlin.properties.Delegates

class UserDetails {

    var userId by Delegates.notNull<String>()
    var duration by Delegates.notNull<Long>()
    var data by Delegates.notNull<Long>()

}