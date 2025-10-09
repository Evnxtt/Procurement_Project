package com.example.procurement.model

import kotlinx.serialization.Serializable
import com.example.procurement.model.interfaces.IApprovable

@Serializable
data class PurchaseOrder(
    val id: Int,
    val vendor: Vendor,
    val items: List<Item>,
    val createdBy: User,
    var approvedBy: User? = null,
    var status: String = "Draft"
) : IApprovable {

    override fun approve(): Boolean {
        status = "Approved"
        return true
    }

    override fun reject(reason: String) {
        status = "Rejected: $reason"
    }

    fun totalAmount(): Double = items.sumOf { it.totalPrice() }
}
