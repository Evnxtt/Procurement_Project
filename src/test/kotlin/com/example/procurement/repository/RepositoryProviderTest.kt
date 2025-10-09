package com.example.procurement.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RepositoryProviderTest {

    @Test
    fun `should provide PurchaseRequisitionRepository instance`() {
        assertNotNull(RepositoryProvider.requisitionRepo)
        assertTrue(RepositoryProvider.requisitionRepo is PurchaseRequisitionRepository)
    }

    @Test
    fun `should provide RequestForQuotationRepository instance`() {
        assertNotNull(RepositoryProvider.rfqRepo)
        assertTrue(RepositoryProvider.rfqRepo is RequestForQuotationRepository)
    }

    @Test
    fun `should provide PurchaseOrderRepository instance`() {
        assertNotNull(RepositoryProvider.orderRepo)
        assertTrue(RepositoryProvider.orderRepo is PurchaseOrderRepository)
    }

    @Test
    fun `should provide GoodsReceiptRepository instance`() {
        assertNotNull(RepositoryProvider.goodsReceiptRepo)
        assertTrue(RepositoryProvider.goodsReceiptRepo is GoodsReceiptRepository)
    }

    @Test
    fun `should provide InvoiceRepository instance`() {
        assertNotNull(RepositoryProvider.invoiceRepo)
        assertTrue(RepositoryProvider.invoiceRepo is InvoiceRepository)
    }

    @Test
    fun `should provide PaymentRepository instance`() {
        assertNotNull(RepositoryProvider.paymentRepo)
        assertTrue(RepositoryProvider.paymentRepo is PaymentRepository)
    }

    @Test
    fun `should ensure all repositories are singleton instances`() {
        // Pastikan setiap pemanggilan mengembalikan instance yang sama
        assertSame(RepositoryProvider.requisitionRepo, RepositoryProvider.requisitionRepo)
        assertSame(RepositoryProvider.rfqRepo, RepositoryProvider.rfqRepo)
        assertSame(RepositoryProvider.orderRepo, RepositoryProvider.orderRepo)
        assertSame(RepositoryProvider.goodsReceiptRepo, RepositoryProvider.goodsReceiptRepo)
        assertSame(RepositoryProvider.invoiceRepo, RepositoryProvider.invoiceRepo)
        assertSame(RepositoryProvider.paymentRepo, RepositoryProvider.paymentRepo)
    }
}
