package com.example.procurement.model

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseRequisition(
    val id: Int,
    val title: String,
    val requester: User,
    val items: List<Item>,
    var status: String = "Pending",
    val remarks: String? = null
)
