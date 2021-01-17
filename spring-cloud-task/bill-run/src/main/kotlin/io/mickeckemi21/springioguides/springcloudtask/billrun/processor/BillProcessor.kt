package io.mickeckemi21.springioguides.springcloudtask.billrun.processor

import io.mickeckemi21.springioguides.springcloudtask.billrun.model.Bill
import io.mickeckemi21.springioguides.springcloudtask.billrun.model.Usage
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class BillProcessor : ItemProcessor<Usage, Bill> {

    companion object {
        const val dataUsageRate = 0.001
        const val minutesUsageRate = 0.01
    }

    override fun process(item: Usage): Bill {
        val calculatedBillAmount = item.dataUsage * dataUsageRate + item.minutes * minutesUsageRate
        return Bill().apply {
            id = item.id
            firstName = item.firstName
            lastName = item.lastName
            dataUsage = item.dataUsage
            minutes = item.minutes
            billAmount = calculatedBillAmount
        }
    }
}
