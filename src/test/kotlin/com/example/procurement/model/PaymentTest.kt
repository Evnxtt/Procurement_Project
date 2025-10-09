package com.example.procurement.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PaymentTest {

    @Test
    fun `should create payment with valid data`() {
        val user = User(1, "Admin", "admin@company.com", Role.FINANCE)
        val payment = Payment(
            id = 101,
            invoiceId = 2001,
            amount = 1500.0,
            processedBy = user,
            status = "Pending",
            paymentDate = LocalDate.of(2025, 1, 10)
        )

        assertEquals(101, payment.id)
        assertEquals(2001, payment.invoiceId)
        assertEquals(1500.0, payment.amount)
        assertEquals("Pending", payment.status)
        assertEquals(LocalDate.of(2025, 1, 10), payment.paymentDate)
        assertEquals(user, payment.processedBy)
    }

    @Test
    fun `should mark payment as paid`() {
        val user = User(2, "Finance Officer", "finance@company.com", Role.FINANCE)
        val payment = Payment(
            id = 102,
            invoiceId = 2002,
            amount = 5000.0,
            processedBy = user
        )

        val result = payment.markAsPaid()

        assertTrue(result)
        assertEquals("Paid", payment.status)
    }

    @Test
    fun `should maintain same amount and processor after marking as paid`() {
        val user = User(3, "Cashier", "cashier@company.com", Role.FINANCE)
        val payment = Payment(
            id = 103,
            invoiceId = 2003,
            amount = 750.0,
            processedBy = user
        )

        payment.markAsPaid()

        assertEquals(750.0, payment.amount)
        assertEquals(user, payment.processedBy)
        assertEquals("Paid", payment.status)
    }
}
