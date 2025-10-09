package com.example.procurement.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PurchaseRequisitionTest {

    @Test
    fun `should create purchase requisition with valid data`() {
        val requester = User(1, "John Requester", "john@company.com", Role.REQUESTER)
        val items = listOf(
            Item(1, "Laptop", 2, 15000.0),
            Item(2, "Monitor", 3, 3000.0)
        )

        val requisition = PurchaseRequisition(
            id = 201,
            title = "Office Equipment Request",
            requester = requester,
            items = items,
            status = "Pending",
            remarks = "Need new equipment for new hires"
        )

        assertEquals(201, requisition.id)
        assertEquals("Office Equipment Request", requisition.title)
        assertEquals(requester, requisition.requester)
        assertEquals(2, requisition.items.size)
        assertEquals("Pending", requisition.status)
        assertEquals("Need new equipment for new hires", requisition.remarks)
    }

    @Test
    fun `should default status to Pending when not provided`() {
        val requester = User(2, "Alice", "alice@company.com", Role.REQUESTER)
        val items = listOf(Item(3, "Keyboard", 5, 200.0))

        val requisition = PurchaseRequisition(
            id = 202,
            title = "Keyboard Request",
            requester = requester,
            items = items
        )

        assertEquals("Pending", requisition.status)
        assertNull(requisition.remarks)
    }

    @Test
    fun `should contain correct item details`() {
        val requester = User(3, "Bob", "bob@company.com", Role.REQUESTER)
        val items = listOf(Item(4, "Desk Chair", 10, 750.0))
        val requisition = PurchaseRequisition(
            id = 203,
            title = "Furniture Request",
            requester = requester,
            items = items
        )

        val item = requisition.items.first()
        assertEquals("Desk Chair", item.name)
        assertEquals(10, item.quantity)
        assertEquals(750.0, item.unitPrice)
    }
}
