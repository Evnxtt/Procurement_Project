package com.example.procurement.model

import kotlin.test.Test
import kotlin.test.assertEquals

class ItemTest {

    @Test
    fun `should create item with correct properties`() {
        val item = Item(
            id = 1,
            name = "Laptop",
            quantity = 2,
            unitPrice = 15000000.0
        )

        assertEquals(1, item.id)
        assertEquals("Laptop", item.name)
        assertEquals(2, item.quantity)
        assertEquals(15000000.0, item.unitPrice)
    }

    @Test
    fun `should correctly calculate total price`() {
        val item = Item(
            id = 2,
            name = "Printer",
            quantity = 3,
            unitPrice = 2500000.0
        )

        val expectedTotal = 3 * 2500000.0
        assertEquals(expectedTotal, item.totalPrice())
    }

    @Test
    fun `should return zero total price when quantity is zero`() {
        val item = Item(
            id = 3,
            name = "Mouse",
            quantity = 0,
            unitPrice = 100000.0
        )

        assertEquals(0.0, item.totalPrice())
    }

    @Test
    fun `should return zero total price when unit price is zero`() {
        val item = Item(
            id = 4,
            name = "Keyboard",
            quantity = 5,
            unitPrice = 0.0
        )

        assertEquals(0.0, item.totalPrice())
    }
}
