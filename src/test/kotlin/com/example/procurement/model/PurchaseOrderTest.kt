package com.example.procurement.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PurchaseOrderTest {

    private val vendor = Vendor(
        id = 1,
        name = "PT. Sumber Jaya",
        contactEmail = "vendor@example.com",
        phoneNumber = "08123456789"
    )

    private val creator = User(
        id = 100,
        name = "Budi",
        email = "budi@company.com",
        role = Role.REQUESTER
    )

    private val approver = User(
        id = 101,
        name = "Andi",
        email = "andi@company.com",
        role = Role.APPROVER
    )

    private val items = listOf(
        Item(id = 1, name = "Laptop", quantity = 2, unitPrice = 15000000.0),
        Item(id = 2, name = "Printer", quantity = 1, unitPrice = 2500000.0)
    )

    @Test
    fun `should create purchase order with default draft status`() {
        val po = PurchaseOrder(
            id = 1,
            vendor = vendor,
            items = items,
            createdBy = creator
        )

        assertEquals("Draft", po.status)
        assertNull(po.approvedBy)
        assertEquals(2, po.items.size)
    }

    @Test
    fun `should calculate total amount correctly`() {
        val po = PurchaseOrder(
            id = 2,
            vendor = vendor,
            items = items,
            createdBy = creator
        )
    }
}