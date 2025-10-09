package com.example.procurement.service

import com.example.procurement.model.*

class ProcurementService(
    private val requisitionService: PurchaseRequisitionService = PurchaseRequisitionService(),
    private val rfqService: RequestForQuotationService = RequestForQuotationService(),
    private val orderService: PurchaseOrderService = PurchaseOrderService(),
    private val goodsService: GoodsReceiptService = GoodsReceiptService(),
    private val invoiceService: InvoiceService = InvoiceService(),
    private val paymentService: PaymentService = PaymentService()
) {

    fun createRequisition(req: PurchaseRequisition): PurchaseRequisition {
        return requisitionService.createRequisition(req)
    }

    fun requestQuotation(rfq: RequestForQuotation): RequestForQuotation {
        return rfqService.createRFQ(rfq)
    }

    fun createPurchaseOrder(po: PurchaseOrder): PurchaseOrder {
        return orderService.createOrder(po)
    }

    fun receiveGoods(grn: GoodsReceiptNote): GoodsReceiptNote {
        return goodsService.receiveGoods(grn)
    }

    fun approveInvoice(invoice: Invoice): Invoice? {
        return invoiceService.approveInvoice(invoice.id)
    }

    fun createInvoice(invoice: Invoice): Invoice {
        return invoiceService.createInvoice(invoice)
    }

    fun makePayment(payment: Payment): Payment {
        return paymentService.makePayment(payment)
    }
}
