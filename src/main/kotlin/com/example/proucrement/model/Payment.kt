package com.example.procurement.model

import kotlinx.serialization.Serializable
import com.example.procurement.model.interfaces.IPayable

@Serializable
data class Payment(
    val id: Int,
    val invoiceId: Int,
    val amount: Double,
    val processedBy: User,
    var status: String = "Pending"
) : IPayable {
    override fun markAsPaid(): Boolean {
        status = "Paid"
        return true
    }
}
