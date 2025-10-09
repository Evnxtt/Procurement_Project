package com.example.procurement.repository

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PurchaseRequisitionRepositoryTest {

    private val repository = PurchaseRequisitionRepository()

    private val requester = User(
        id = 1,
        name = "John Doe",
        email = "john@company.com",
        role = Role.REQUESTER
    )

    private val items = listOf(
        Item(id = 1, name = "Printer Ink", quantity = 2, unitPrice = 250000.0),
        Item(id = 2, name = "Paper A4", quantity = 5, unitPrice = 50000.0)
    )

    @Test
    fun `should add new requisition`() {
        val req = PurchaseRequisition(
            id = 0,
            title = "Office Supplies",
            requester = requester,
            items = items
        )

        val saved = repository.add(req)

        assertEquals(1, saved.id)
        assertEquals(1, repository.getAll().size)
        assertEquals("Office Supplies", saved.title)
    }

    @Test
    fun `should get all requisitions`() {
        repository.add(
            PurchaseRequisition(
                id = 0,
                title = "Stationery",
                requester = requester,
                items = items
            )
        )
        repository.add(
            PurchaseRequisition(
                id = 0,
                title = "IT Equipment",
                requester = requester,
                items = items
            )
        )

        val all = repository.getAll()

        assertEquals(2, all.size)
        assertTrue(all.any { it.title == "Stationery" })
        assertTrue(all.any { it.title == "IT Equipment" })
    }

    @Test
    fun `should find requisition by id`() {
        val saved = repository.add(
            PurchaseRequisition(
                id = 0,
                title = "New Chairs",
                requester = requester,
                items = items
            )
        )

        val found = repository.findById(saved.id)

        assertNotNull(found)
        assertEquals("New Chairs", found?.title)
    }

    @Test
    fun `should return null when requisition id not found`() {
        val found = repository.findById(999)
        assertNull(found)
    }

    @Test
    fun `should update existing requisition`() {
        val saved = repository.add(
            PurchaseRequisition(
                id = 0,
                title = "Initial Request",
                requester = requester,
                items = items
            )
        )

        val updated = saved.copy(status = "Approved", remarks = "Approved by manager")
        val result = repository.update(updated)

        assertNotNull(result)
        assertEquals("Approved", result?.status)
        assertEquals("Approved by manager", result?.remarks)
    }

    @Test
    fun `should return null when updating non-existent requisition`() {
        val fake = PurchaseRequisition(
            id = 999,
            title = "Ghost Request",
            requester = requester,
            items = items
        )

        val result = repository.update(fake)
        assertNull(result)
    }
}
