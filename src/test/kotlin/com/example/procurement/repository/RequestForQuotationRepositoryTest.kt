package com.example.procurement.repository

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RequestForQuotationRepositoryTest {

    private lateinit var repository: RequestForQuotationRepository
    private lateinit var vendor: Vendor
    private lateinit var user: User
    private lateinit var items: List<Item>

    @BeforeEach
    fun setup() {
        repository = RequestForQuotationRepository()
        vendor = Vendor(
            id = 1,
            name = "PT Sumber Makmur",
            contactEmail = "contact@sumber.com",
            phoneNumber = "08123456789"
        )
        user = User(
            id = 1,
            name = "John Doe",
            email = "john@company.com",
            role = Role.PROCUREMENT_OFFICER
        )
        items = listOf(
            Item(id = 1, name = "Kertas A4", quantity = 10, unitPrice = 5000.0),
            Item(id = 2, name = "Tinta Printer", quantity = 2, unitPrice = 150000.0)
        )
    }

    @Test
    fun `should add a new RFQ successfully`() {
        val rfq = RequestForQuotation(
            id = 0,
            vendor = vendor,
            requestedItems = items,
            createdBy = user,
            status = "Sent",
            remarks = "Butuh segera"
        )

        val saved = repository.add(rfq)

        assertEquals(1, saved.id)
        assertEquals("PT Sumber Makmur", saved.vendor.name)
        assertEquals(2, saved.requestedItems.size)
        assertEquals(1, repository.getAll().size)
    }

    @Test
    fun `should retrieve all RFQs`() {
        repository.add(RequestForQuotation(0, vendor, items, user))
        repository.add(RequestForQuotation(0, vendor, items, user))

        val all = repository.getAll()

        assertEquals(2, all.size)
    }

    @Test
    fun `should find RFQ by id`() {
        val rfq1 = repository.add(RequestForQuotation(0, vendor, items, user))
        val rfq2 = repository.add(RequestForQuotation(0, vendor, items, user))

        val found = repository.findById(rfq1.id)

        assertNotNull(found)
        assertEquals(rfq1.id, found!!.id)
    }

    @Test
    fun `should return null when RFQ id not found`() {
        val found = repository.findById(999)
        assertNull(found)
    }

    @Test
    fun `should update an existing RFQ`() {
        val saved = repository.add(RequestForQuotation(0, vendor, items, user))
        val updated = saved.copy(status = "Reviewed", remarks = "Cek ulang harga")

        val result = repository.update(updated)

        assertNotNull(result)
        assertEquals("Reviewed", result!!.status)
        assertEquals("Cek ulang harga", result.remarks)
    }

    @Test
    fun `should return null when updating non-existent RFQ`() {
        val rfq = RequestForQuotation(99, vendor, items, user, status = "Invalid")
        val result = repository.update(rfq)
        assertNull(result)
    }
}
