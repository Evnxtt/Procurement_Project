package com.example.procurement.service

import com.example.procurement.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ProcurementServiceTest {

    private val service = ProcurementService()

    // Dummy users dan vendor
    private val requester = User(
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
    fun `should execute full procurement workflow successfully`() {
        // Step 1 — Create Purchase Requisition
        val requisition = PurchaseRequisition(
            id = 1,
            title = "Office Equipment Request",
            requester = requester,
            items = items
        )
        val createdReq = service.createRequisition(requisition)
        assertEquals("PENDING", createdReq.status)

        // Step 2 — Request For Quotation
        val rfq = RequestForQuotation(
            id = 1,
            vendor = vendor,
            requestedItems = items,    // ✅ sesuai model
            createdBy = requester      // ✅ sesuai model
        )
        val createdRFQ = service.requestQuotation(rfq)
        assertEquals("SENT", createdRFQ.status)

        // Step 3 — Create Purchase Order
        val po = PurchaseOrder(
            id = 1,
            vendor = vendor,
            items = items,
            createdBy = requester
        )
        val createdPO = service.createPurchaseOrder(po)
        assertEquals("CREATED", createdPO.status)

        // Step 4 — Receive Goods
        val grn = GoodsReceiptNote(
            id = 1,
            purchaseOrderId = po.id,
            receivedItems = items,     // ✅ sesuai model
            receivedBy = approver,
            receivedDate = LocalDate.now(),
            status = "Received"
        )
        val received = service.receiveGoods(grn)
        assertEquals("Received", received.status)

        // Step 5 — Approve Invoice
        val invoice = Invoice(
            id = 1,
            vendor = vendor,
            relatedPO = po.id,
            totalAmount = po.totalAmount(),
            createdBy = requester
        )
        service.createInvoice(invoice)
        val approvedInvoice = service.approveInvoice(invoice)
        assertEquals("APPROVED", approvedInvoice?.status)

        // Step 6 — Make Payment
        val payment = Payment(
            id = 1,
            invoiceId = invoice.id,
            amount = invoice.totalAmount,
            processedBy = approver
        )
        val completedPayment = service.makePayment(payment)
        assertEquals("COMPLETED", completedPayment.status)
        assertNotNull(completedPayment.paymentDate)
    }
}
