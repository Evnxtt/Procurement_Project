package com.example.procurement.service

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InvoiceServiceTest {

    private val service = InvoiceService()

    // 🔹 Buat objek dummy User dan Vendor langsung
    private val vendor = Vendor(
        id = 1,
        name = "PT Sumber Jaya",
        contactEmail = "vendor@sumber.com",
        phoneNumber = "08123456789"
    )

    private val user = User(
        id = 101,
        name = "Budi",
        email = "budi@company.com",
        role = Role.FINANCE
    )

    @Test
    fun `should create invoice with Unverified status`() {
        val invoice = Invoice(
            id = 1,
            vendor = vendor,
            relatedPO = 123,
            totalAmount = 5000.0,
            createdBy = user
        )

        val result = service.createInvoice(invoice)
        assertEquals("PENDING", result.status)
    }

    @Test
    fun `should approve invoice`() {
        val vendor = Vendor(1, "PT Sumber Makmur", "contact@sumber.com", "08123456789")
        val user = User(2, "Budi", "budi@company.com", Role.FINANCE)

        val invoice = Invoice(
            id = 2,
            vendor = vendor,
            relatedPO = 123,
            totalAmount = 5000.0,
            createdBy = user
        )

        // ✅ simpan dulu ke repo via service
        service.createInvoice(invoice)

        // ✅ baru approve
        val result = service.approveInvoice(invoice.id)

        assertNotNull(result)
        assertEquals("APPROVED", result!!.status)
    }


    @Test
    fun `should reject invoice with reason`() {
        val invoice = Invoice(
            id = 3,
            vendor = vendor,
            relatedPO = 789,
            totalAmount = 20000.0,
            createdBy = user
        )
        service.createInvoice(invoice) // <-- tambahkan ini
        val result = service.rejectInvoice(invoice.id, reason = "Document mismatch")
        assertNotNull(result)
        assertEquals("REJECTED", result?.status)
        assertEquals("Document mismatch", result?.remarks)
    }
}
