package com.example.procurement.repository

import com.example.procurement.model.Payment

class PaymentRepository {
    private val payments = mutableListOf<Payment>()
    private var nextId = 1

    fun add(payment: Payment): Payment {
        val withId = payment.copy(id = nextId++)
        payments.add(withId)
        return withId
    }

    fun getAll(): List<Payment> = payments
}
