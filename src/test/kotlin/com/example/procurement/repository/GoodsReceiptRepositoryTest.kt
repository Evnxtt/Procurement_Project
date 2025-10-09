package com.example.procurement.repository

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GoodsReceiptRepositoryTest {

    private lateinit var repository: GoodsReceiptRepository

    @BeforeEach
    fun setup() {
        repository = GoodsReceiptRepository()
    }

    @Test
    fun `should add goods receipt note and assign incremental ID`() {
        val user = User(1, "Receiver", "recv@company.com", Role.PROCUREMENT_OFFICER)
        val items = listOf(Item(1, "Laptop", 2, 15000.0))

        val note = GoodsReceiptNote(
            id = 0, // akan diganti otomatis
            purchaseOrderId = 5001,
            receivedItems = items,
            receivedBy = user,
            receivedDate = LocalDate.of(2025, 10, 9)
        )

        val added = repository.add(note)

        assertEquals(1, added.id)
        assertEquals(1, repository.getAll().size)
        assertEquals("Laptop", added.receivedItems.first().name)
    }

    @Test
    fun `should assign unique IDs for each added note`() {
        val user = User(2, "Officer", "officer@company.com", Role.PROCUREMENT_OFFICER)
        val note1 = GoodsReceiptNote(0, 6001, listOf(Item(1, "Printer", 1, 2500.0)), user)
        val note2 = GoodsReceiptNote(0, 6002, listOf(Item(2, "Scanner", 2, 3500.0)), user)

        val added1 = repository.add(note1)
        val added2 = repository.add(note2)

        assertEquals(1, added1.id)
        assertEquals(2, added2.id)
        assertNotEquals(added1.id, added2.id)
    }

    @Test
    fun `should retrieve all added goods receipt notes`() {
        val user = User(3, "Warehouse", "warehouse@company.com", Role.PROCUREMENT_OFFICER)

        repository.add(GoodsReceiptNote(0, 7001, listOf(Item(1, "Cable", 10, 50.0)), user))
        repository.add(GoodsReceiptNote(0, 7002, listOf(Item(2, "Keyboard", 5, 250.0)), user))

        val allNotes = repository.getAll()

        assertEquals(2, allNotes.size)
        assertEquals("Cable", allNotes[0].receivedItems.first().name)
        assertEquals("Keyboard", allNotes[1].receivedItems.first().name)
    }

    @Test
    fun `should return empty list when no goods receipt added`() {
        val allNotes = repository.getAll()
        assertTrue(allNotes.isEmpty())
    }
}
