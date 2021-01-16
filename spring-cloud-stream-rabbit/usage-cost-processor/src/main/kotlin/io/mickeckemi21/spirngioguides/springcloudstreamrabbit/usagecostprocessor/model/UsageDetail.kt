package io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor.model

import kotlin.properties.Delegates

class UsageDetail {

    var userId by Delegates.notNull<String>()
    var duration by Delegates.notNull<Long>()
    var data by Delegates.notNull<Long>()

}
