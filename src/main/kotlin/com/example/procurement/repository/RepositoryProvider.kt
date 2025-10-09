package com.example.procurement.repository

object RepositoryProvider {
    var requisitionRepo = PurchaseRequisitionRepository()
    var rfqRepo = RequestForQuotationRepository()
    var orderRepo = PurchaseOrderRepository()
    var goodsReceiptRepo = GoodsReceiptRepository()
    var invoiceRepo = InvoiceRepository()
    var paymentRepo = PaymentRepository()
}

