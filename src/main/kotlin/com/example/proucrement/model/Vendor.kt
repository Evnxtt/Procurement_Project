package com.example.procurement.model

import kotlinx.serialization.Serializable

@Serializable
data class Vendor(
    val id: Int,
    val name: String,
    val contactEmail: String,
    val phoneNumber: String
)
