package com.example.procurement.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RequestForQuotationTest {

    @Test
    fun `should create request for quotation with valid data`() {
        val vendor = Vendor(1, "PT Sumber Jaya", "contact@sumberjaya.com", "08123456789")
        val user = User(1, "Procurement Officer", "officer@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(
            Item(1, "Laptop", 5, 15000.0),
            Item(2, "Printer", 2, 2500.0)
        )

        val rfq = RequestForQuotation(
            id = 301,
            vendor = vendor,
            requestedItems = items,
            createdBy = user,
            status = "Sent",
            remarks = "Urgent request for new office setup"
        )

        assertEquals(301, rfq.id)
        assertEquals(vendor, rfq.vendor)
        assertEquals(items, rfq.requestedItems)
        assertEquals(user, rfq.createdBy)
        assertEquals("Sent", rfq.status)
        assertEquals("Urgent request for new office setup", rfq.remarks)
    }

    @Test
    fun `should default status to Sent when not provided`() {
        val vendor = Vendor(2, "PT Teknologi Nusantara", "sales@teknologi.com", "08234567890")
        val user = User(2, "Admin", "admin@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(Item(3, "Monitor", 10, 2000.0))

        val rfq = RequestForQuotation(
            id = 302,
            vendor = vendor,
            requestedItems = items,
            createdBy = user
        )

        assertEquals("Sent", rfq.status)
        assertNull(rfq.remarks)
    }

    @Test
    fun `should contain correct item details`() {
        val vendor = Vendor(3, "PT Office Supplies", "office@supplies.com", "08345678901")
        val user = User(3, "Request Creator", "creator@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(Item(4, "Desk", 20, 800.0))

        val rfq = RequestForQuotation(
            id = 303,
            vendor = vendor,
            requestedItems = items,
            createdBy = user
        )

        val item = rfq.requestedItems.first()
        assertEquals("Desk", item.name)
        assertEquals(20, item.quantity)
        assertEquals(800.0, item.unitPrice)
    }
}
