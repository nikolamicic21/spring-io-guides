package io.mickeckemi21.springioguides.springcloudtask.billrun.model

import kotlin.properties.Delegates

class Usage {

    var id by Delegates.notNull<Long>()
    var firstName by Delegates.notNull<String>()
    var lastName by Delegates.notNull<String>()
    var minutes by Delegates.notNull<Long>()
    var dataUsage by Delegates.notNull<Long>()

}
