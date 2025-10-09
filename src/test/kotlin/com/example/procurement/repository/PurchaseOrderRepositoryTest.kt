package com.example.procurement.repository

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PurchaseOrderRepositoryTest {

    private val repository = PurchaseOrderRepository()

    private val requester = User(
        id = 1,
        name = "John Doe",
        email = "john@company.com",
        role = Role.REQUESTER
    )

    private val approver = User(
        id = 2,
        name = "Jane Smith",
        email = "jane@company.com",
        role = Role.APPROVER
    )

    private val vendor = Vendor(
        id = 1,
        name = "PT Sumber Makmur",
        contactEmail = "contact@sumber.com",
        phoneNumber = "08123456789"
    )

    private val items = listOf(
        Item(id = 1, name = "Kertas A4", quantity = 10, unitPrice = 5000.0),
        Item(id = 2, name = "Pulpen", quantity = 5, unitPrice = 3000.0)
    )

    @Test
    fun `should add new purchase order`() {
        val order = PurchaseOrder(
            id = 0,
            vendor = vendor,
            items = items,
            createdBy = requester
        )

        val saved = repository.add(order)
        assertEquals(1, saved.id)
        assertEquals(1, repository.getAll().size)
    }

    @Test
    fun `should find purchase order by id`() {
        val order = PurchaseOrder(
            id = 0,
            vendor = vendor,
            items = items,
            createdBy = requester
        )

        val saved = repository.add(order)
        val found = repository.findById(saved.id)

        assertNotNull(found)
        assertEquals(saved.id, found?.id)
        assertEquals(saved.vendor.name, found?.vendor?.name)
    }

    @Test
    fun `should return null when id not found`() {
        val notFound = repository.findById(999)
        assertNull(notFound)
    }

    @Test
    fun `should update existing purchase order`() {
        val order = PurchaseOrder(
            id = 0,
            vendor = vendor,
            items = items,
            createdBy = requester
        )

        val saved = repository.add(order)
        val updatedOrder = saved.copy(status = "Approved", approvedBy = approver)

        val updated = repository.update(updatedOrder)
        assertNotNull(updated)
        assertEquals("Approved", updated?.status)
        assertEquals(approver.name, updated?.approvedBy?.name)
    }

    @Test
    fun `should return null when updating non-existent order`() {
        val fakeOrder = PurchaseOrder(
            id = 999,
            vendor = vendor,
            items = items,
            createdBy = requester
        )

        val result = repository.update(fakeOrder)
        assertNull(result)
    }
}
