package com.example.procurement.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GoodsReceiptNoteTest {

    @Test
    fun `should create goods receipt note with valid data`() {
        val user = User(1, "John Receiver", "john@warehouse.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(
            Item(1, "Laptop", 2, 15000.0),
            Item(2, "Mouse", 5, 100.0)
        )

        val receipt = GoodsReceiptNote(
            id = 501,
            purchaseOrderId = 3001,
            receivedItems = items,
            receivedBy = user,
            receivedDate = LocalDate.of(2025, 10, 8),
            status = "Awaiting Receipt"
        )

        assertEquals(501, receipt.id)
        assertEquals(3001, receipt.purchaseOrderId)
        assertEquals(user, receipt.receivedBy)
        assertEquals("Awaiting Receipt", receipt.status)
        assertEquals(2, receipt.receivedItems.size)
        assertEquals(LocalDate.of(2025, 10, 8), receipt.receivedDate)
    }

    @Test
    fun `should mark goods receipt as received`() {
        val user = User(2, "Warehouse Staff", "warehouse@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(Item(10, "Printer", 1, 2500.0))

        val receipt = GoodsReceiptNote(
            id = 502,
            purchaseOrderId = 3002,
            receivedItems = items,
            receivedBy = user
        )

        val result = receipt.receiveGoods()

        assertTrue(result)
        assertEquals("Received", receipt.status)
    }

    @Test
    fun `should maintain same items after receiving goods`() {
        val user = User(3, "Receiver 2", "recv2@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(Item(20, "Projector", 1, 5000.0))

        val receipt = GoodsReceiptNote(
            id = 503,
            purchaseOrderId = 3003,
            receivedItems = items,
            receivedBy = user
        )

        receipt.receiveGoods()

        assertEquals(items, receipt.receivedItems)
        assertEquals("Received", receipt.status)
        assertEquals(user, receipt.receivedBy)
    }
}
