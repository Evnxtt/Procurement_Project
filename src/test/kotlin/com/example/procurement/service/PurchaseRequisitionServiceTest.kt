package com.example.procurement.service

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PurchaseRequisitionServiceTest {

    private val service = PurchaseRequisitionService()

    private val requester = User(
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
    fun `should create requisition with Pending status`() {
        val req = PurchaseRequisition(
            id = 1,
            title = "Office Equipment Request",
            requester = requester,
            items = items
        )

        val result = service.createRequisition(req)

        assertEquals("Pending", result.status)
        assertEquals("Office Equipment Request", result.title)
        assertEquals(2, result.items.size)
    }

    @Test
    fun `should approve requisition`() {
        val req = PurchaseRequisition(
            id = 2,
            title = "Monitor Request",
            requester = requester,
            items = items
        )

        val approved = service.approveRequisition(req.id)

        assertEquals("Approved", approved?.status)
    }

    @Test
    fun `should reject requisition with reason`() {
        val req = PurchaseRequisition(
            id = 3,
            title = "Printer Request",
            requester = requester,
            items = items
        )

        val rejected = service.rejectRequisition(req.id, "Not needed this quarter")

        assertEquals("Rejected", rejected?.status)
        assertEquals("Not needed this quarter", rejected?.remarks)
    }
}
