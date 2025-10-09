package com.example.procurement.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import com.example.procurement.model.interfaces.IPayable
import java.time.LocalDate
import kotlinx.serialization.Contextual

@Serializable
data class Payment(
    val id: Int,
    val invoiceId: Int,
    val amount: Double,
    val processedBy: User,
    var status: String = "Pending",
    @Contextual val paymentDate: LocalDate? = null
) : IPayable {
    override fun markAsPaid(): Boolean {
        status = "Paid"
        return true
    }
}
