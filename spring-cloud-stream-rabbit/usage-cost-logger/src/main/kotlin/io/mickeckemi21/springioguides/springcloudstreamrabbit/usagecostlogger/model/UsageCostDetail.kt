package io.mickeckemi21.springioguides.springcloudstreamrabbit.usagecostlogger.model

class UsageCostDetail {

    var userId: String? = null
    var dataCost: Double? = null
    var callCost: Double? = null

    override fun toString(): String {
        return "UserCostDetail(userId=$userId, dataCost=$dataCost, callCost=$callCost)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UsageCostDetail

        if (userId != other.userId) return false
        if (dataCost != other.dataCost) return false
        if (callCost != other.callCost) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId?.hashCode() ?: 0
        result = 31 * result + (dataCost?.hashCode() ?: 0)
        result = 31 * result + (callCost?.hashCode() ?: 0)
        return result
    }

}
