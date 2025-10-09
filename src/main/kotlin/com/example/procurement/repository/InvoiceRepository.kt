package com.example.procurement.repository

import com.example.procurement.model.Invoice

class InvoiceRepository {
    private val invoices = mutableListOf<Invoice>()
    private var nextId = 1

    fun add(invoice: Invoice): Invoice {
        val withId = invoice.copy(id = nextId++)
        invoices.add(withId)
        return withId
    }

    fun getAll(): List<Invoice> = invoices

    fun findById(id: Int): Invoice? = invoices.find { it.id == id }

    fun update(invoice: Invoice): Invoice? {
        val index = invoices.indexOfFirst { it.id == invoice.id }
        if (index != -1) {
            invoices[index] = invoice
            return invoice
        }
        return null
    }
}
