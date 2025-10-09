package com.example.procurement.repository

object RepositoryProvider {
    val requisitionRepo = PurchaseRequisitionRepository()
    val rfqRepo = RequestForQuotationRepository()
    val orderRepo = PurchaseOrderRepository()
    val goodsReceiptRepo = GoodsReceiptRepository()
    val invoiceRepo = InvoiceRepository()
    val paymentRepo = PaymentRepository()
}
