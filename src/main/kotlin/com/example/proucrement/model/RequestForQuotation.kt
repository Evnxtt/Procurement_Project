package com.example.procurement.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestForQuotation(
    val id: Int,
    val vendor: Vendor,
    val requestedItems: List<Item>,
    var createdBy: User,
    var status: String = "Sent"
)
