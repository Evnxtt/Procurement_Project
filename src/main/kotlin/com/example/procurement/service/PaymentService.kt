package com.example.procurement.service

import com.example.procurement.model.Payment
import com.example.procurement.repository.RepositoryProvider

class PaymentService {
    private val repo = RepositoryProvider.paymentRepo

    fun makePayment(payment: Payment): Payment =
        repo.add(payment.copy(status = "COMPLETED", paymentDate = payment.paymentDate ?: java.time.LocalDate.now()))

    fun getAllPayments(): List<Payment> = repo.getAll()
}
