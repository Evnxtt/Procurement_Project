package com.example.procurement.repository

import com.example.procurement.model.Invoice
import com.example.procurement.model.Role
import com.example.procurement.model.User
import com.example.procurement.model.Vendor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InvoiceRepositoryTest {

    private lateinit var repository: InvoiceRepository
    private lateinit var vendor: Vendor
    private lateinit var creator: User

    @BeforeEach
    fun setup() {
        repository = InvoiceRepository()
        vendor = Vendor(1, "PT Sumber Makmur", "contact@sumber.com", "08123456789")
        creator = User(1, "John Creator", "john@company.com", Role.PROCUREMENT_OFFICER)
    }

    @Test
    fun `should add invoice and assign incremental ID`() {
        val invoice = Invoice(
            id = 0,
            vendor = vendor,
            relatedPO = 1001,
            totalAmount = 25000.0,
            createdBy = creator
        )

        val added = repository.add(invoice)

        Assertions.assertEquals(1, added.id)
        Assertions.assertEquals(1, repository.getAll().size)
        Assertions.assertEquals("PT Sumber Makmur", added.vendor.name)
    }

    @Test
    fun `should assign unique IDs for multiple invoices`() {
        val invoice1 = Invoice(0, vendor, 2001, 5000.0, creator)
        val invoice2 = Invoice(0, vendor, 2002, 7500.0, creator)

        val added1 = repository.add(invoice1)
        val added2 = repository.add(invoice2)

        Assertions.assertEquals(1, added1.id)
        Assertions.assertEquals(2, added2.id)
        Assertions.assertNotEquals(added1.id, added2.id)
    }

    @Test
    fun `should find invoice by ID`() {
        val added = repository.add(Invoice(0, vendor, 3001, 15000.0, creator))

        val found = repository.findById(added.id)

        Assertions.assertNotNull(found)
        Assertions.assertEquals(added.id, found?.id)
        Assertions.assertEquals(15000.0, found?.totalAmount)
    }

    @Test
    fun `should return null when finding non-existing invoice`() {
        val found = repository.findById(999)
        Assertions.assertNull(found)
    }

    @Test
    fun `should update existing invoice`() {
        val added = repository.add(Invoice(0, vendor, 4001, 10000.0, creator))
        val updated = added.copy(status = "Approved", remarks = "Verified by finance")

        val result = repository.update(updated)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("Approved", result?.status)
        Assertions.assertEquals("Verified by finance", result?.remarks)
    }

    @Test
    fun `should return null when updating non-existing invoice`() {
        val nonExisting = Invoice(99, vendor, 5001, 25000.0, creator)
        val result = repository.update(nonExisting)
        Assertions.assertNull(result)
    }

    @Test
    fun `should retrieve all invoices`() {
        repository.add(Invoice(0, vendor, 6001, 8000.0, creator))
        repository.add(Invoice(0, vendor, 6002, 9000.0, creator))

        val allInvoices = repository.getAll()

        Assertions.assertEquals(2, allInvoices.size)
        Assertions.assertEquals(8000.0, allInvoices[0].totalAmount)
        Assertions.assertEquals(9000.0, allInvoices[1].totalAmount)
    }

    @Test
    fun `should return empty list when no invoice added`() {
        Assertions.assertTrue(repository.getAll().isEmpty())
    }
}