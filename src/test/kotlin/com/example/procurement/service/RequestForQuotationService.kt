package com.example.procurement.service

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RequestForQuotationServiceTest {

    private val service = RequestForQuotationService()

    private val vendor = Vendor(
        id = 1,
        name = "PT Sumber Jaya",
        contactEmail = "vendor@sumber.com",
        phoneNumber = "08123456789"
    )

    private val creator = User(
        id = 1,
        name = "Budi",
        email = "budi@company.com",
        role = Role.REQUESTER
    )

    private val items = listOf(
        Item(id = 1, name = "Laptop", quantity = 2, unitPrice = 15000000.0),
        Item(id = 2, name = "Printer", quantity = 1, unitPrice = 2500000.0)
    )

    @Test
    fun `should create RFQ with Sent status`() {
        val rfq = RequestForQuotation(
            id = 1,
            vendor = vendor,
            requestedItems = items,
            createdBy = creator
        )

        val result = service.createRFQ(rfq)

        assertEquals("SENT", result.status)
        assertEquals(vendor.name, result.vendor.name)
        assertEquals(2, result.requestedItems.size)
    }

    @Test
    fun `should update RFQ status`() {
        val rfq = RequestForQuotation(
            id = 2,
            vendor = vendor,
            requestedItems = items,
            createdBy = creator
        )

        // First create the RFQ
        val created = service.createRFQ(rfq)
        
        val updated = service.updateStatus(created.id, "Closed")

        assertEquals("Closed", updated?.status)
    }
}
