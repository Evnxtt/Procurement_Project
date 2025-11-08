    package com.example.procurement.service

    import com.example.procurement.model.*
    import org.junit.jupiter.api.Assertions.*
    import org.junit.jupiter.api.Test

    class PurchaseOrderServiceTest {

        private val service = PurchaseOrderService()

        // 🔹 Buat user dan vendor nyata
        private val creator = User(
            id = 1,
            name = "Budi",
            email = "budi@company.com",
            role = Role.REQUESTER
        )

        private val approver = User(
            id = 2,
            name = "Andi",
            email = "andi@company.com",
            role = Role.APPROVER
        )

        private val vendor = Vendor(
            id = 1,
            name = "PT Sumber Jaya",
            contactEmail = "vendor@sumber.com",
            phoneNumber = "08123456789"
        )

        private val items = listOf(
            Item(id = 1, name = "Laptop", quantity = 2, unitPrice = 15000000.0),
            Item(id = 2, name = "Printer", quantity = 1, unitPrice = 2500000.0)
        )

        @Test
        fun `should create order with default Draft status`() {
            val po = PurchaseOrder(
                id = 1,
                vendor = vendor,
                items = items,
                createdBy = creator
            )

            val created = service.createOrder(po)
            assertEquals("CREATED", created.status)
            assertEquals(vendor.name, created.vendor.name)
            assertEquals(2, created.items.size)
        }

        @Test
        fun `should approve purchase order`() {
            val po = PurchaseOrder(
                id = 2,
                vendor = vendor,
                items = items,
                createdBy = creator
            )
            
            // First create the order
            val created = service.createOrder(po)

            val approved = service.approveOrder(created.id)
            assertEquals("APPROVED", approved?.status)
        }

        @Test
        fun `should reject purchase order with reason`() {
            val po = PurchaseOrder(
                id = 3,
                vendor = vendor,
                items = items,
                createdBy = creator
            )

            // First create the order
            val created = service.createOrder(po)
            
            val rejected = service.rejectOrder(created.id, "Budget exceeded")
            assertEquals("REJECTED", rejected?.status)
            assertEquals("Budget exceeded", rejected?.remarks)
        }

        @Test
        fun `should calculate total amount correctly`() {
            val po = PurchaseOrder(
                id = 4,
                vendor = vendor,
                items = items,
                createdBy = creator
            )

            val total = po.totalAmount()
            val expected = 2 * 15000000.0 + 1 * 2500000.0
            assertEquals(expected, total)
        }
    }
