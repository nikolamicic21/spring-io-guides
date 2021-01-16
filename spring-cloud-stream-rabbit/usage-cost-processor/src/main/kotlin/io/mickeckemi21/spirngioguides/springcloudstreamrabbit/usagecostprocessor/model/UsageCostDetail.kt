package io.mickeckemi21.spirngioguides.springcloudstreamrabbit.usagecostprocessor.model

import kotlin.properties.Delegates

class UsageCostDetail {

    var userId by Delegates.notNull<String>()
    var callCost by Delegates.notNull<Double>()
    var dataCost by Delegates.notNull<Double>()

}
