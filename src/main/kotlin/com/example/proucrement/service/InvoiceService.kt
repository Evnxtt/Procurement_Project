package com.example.procurement.service

import com.example.procurement.model.Invoice
import com.example.procurement.repository.RepositoryProvider

class InvoiceService {
    private val repo = RepositoryProvider.invoiceRepo

    fun createInvoice(invoice: Invoice): Invoice =
        repo.add(invoice.copy(status = "PENDING"))

    fun getAllInvoices(): List<Invoice> = repo.getAll()

    fun rejectInvoice(id: Int, reason: String): Invoice? {
        val invoice = repo.findById(id) ?: return null
        val updated = invoice.copy(status = "REJECTED", remarks = reason)
        return repo.update(updated)
    }
}
