package com.example.procurement.model

import kotlinx.serialization.Serializable
import com.example.procurement.model.interfaces.IPayable
import java.time.LocalDate
import com.example.procurement.model.LocalDateSerializer


@Serializable
data class Payment(
    val id: Int,
    val invoiceId: Int,
    val amount: Double,
    val processedBy: User,
    var status: String = "Pending",
    @Serializable(with = LocalDateSerializer::class)
    val paymentDate: LocalDate? = null
) : IPayable {
    override fun markAsPaid(): Boolean {
        status = "Paid"
        return true
    }
}

