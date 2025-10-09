package com.example.procurement.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class InvoiceTest {

    private val vendor = Vendor(
        id = 1,
        name = "PT. Sumber Jaya",
        contactEmail = "vendor@example.com",
        phoneNumber = "08123456789"
    )

    private val user = User(
        id = 101,
        name = "Budi",
        email = "budi@company.com",
        role = Role.FINANCE
    )

    @Test
    fun `should create invoice with default unverified status`() {
        val invoice = Invoice(
            id = 1,
            vendor = vendor,
            relatedPO = 123,
            totalAmount = 5000.0,
            createdBy = user
        )

        assertEquals("Unverified", invoice.status)
        assertNull(invoice.approvedBy)
        assertNull(invoice.remarks)
    }

    @Test
    fun `should approve invoice and set status to Approved`() {
        val invoice = Invoice(
            id = 2,
            vendor = vendor,
            relatedPO = 123,
            totalAmount = 10000.0,
            createdBy = user
        )

        val result = invoice.approve()

        assertEquals(true, result)
        assertEquals("Approved", invoice.status)
    }

    @Test
    fun `should reject invoice and set status and remarks`() {
        val invoice = Invoice(
            id = 3,
            vendor = vendor,
            relatedPO = 123,
            totalAmount = 20000.0,
            createdBy = user
        )

        invoice.reject("Invalid document")

        assertEquals("Rejected", invoice.status)
        assertEquals("Invalid document", invoice.remarks)
    }
}
