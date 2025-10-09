package com.example.procurement.service

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PaymentServiceTest {

    private val service = PaymentService()

    private val financeUser = User(
        id = 1,
        name = "Finance Staff",
        email = "finance@company.com",
        role = Role.FINANCE
    )

    @Test
    fun `should make payment and mark as COMPLETED`() {
        val payment = Payment(
            id = 1,
            invoiceId = 10,
            amount = 10000.0,
            processedBy = financeUser,
            status = "Pending",
            paymentDate = null
        )

        val result = service.makePayment(payment)

        assertEquals("COMPLETED", result.status)
        assertNotNull(result.paymentDate)
    }

    @Test
    fun `should get all payments`() {
        val result = service.getAllPayments()
        assertTrue(result is List<Payment>)
    }
}
