package com.example.procurement.model

import kotlinx.serialization.Serializable
import com.example.procurement.model.interfaces.IApprovable

@Serializable
data class Invoice(
    val id: Int,
    val vendor: Vendor,
    val relatedPO: Int,
    val totalAmount: Double,
    val createdBy: User,
    var approvedBy: User? = null,
    var status: String = "Unverified"
) : IApprovable {

    override fun approve(): Boolean {
        status = "Approved"
        return true
    }

    override fun reject(reason: String) {
        status = "Rejected: $reason"
    }
}
