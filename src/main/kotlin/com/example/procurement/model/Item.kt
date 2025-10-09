package com.example.procurement.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Double
) {
    fun totalPrice(): Double = quantity * unitPrice
}
