package com.example.procurement.service

import com.example.procurement.model.GoodsReceiptNote
import com.example.procurement.model.User
import com.example.procurement.model.Role
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GoodsReceiptServiceTest {

    private val service = GoodsReceiptService()
    private val user = User(1, "John", "john@company.com", Role.FINANCE)

    @Test
    fun `should receive goods successfully`() {
        val note = GoodsReceiptNote(
            id = 1,
            purchaseOrderId = 10,
            receivedItems = emptyList(),
            receivedBy = user,
            receivedDate = LocalDate.now(),
            status = "Received"
        )
        val result = service.receiveGoods(note)
        assertEquals("Received", result.status)
        assertNotNull(result.receivedDate)
    }

    @Test
    fun `should get all receipts`() {
        val result = service.getAllReceipts()
        assertTrue(result is List<GoodsReceiptNote>)
    }
}
