package com.example.procurement.repository

import com.example.procurement.model.RequestForQuotation

class RequestForQuotationRepository {
    private val rfqs = mutableListOf<RequestForQuotation>()
    private var nextId = 1

    fun add(rfq: RequestForQuotation): RequestForQuotation {
        val withId = rfq.copy(id = nextId++)
        rfqs.add(withId)
        return withId
    }

    fun getAll(): List<RequestForQuotation> = rfqs

    fun findById(id: Int): RequestForQuotation? = rfqs.find { it.id == id }

    fun update(rfq: RequestForQuotation): RequestForQuotation? {
        val index = rfqs.indexOfFirst { it.id == rfq.id }
        if (index != -1) {
            rfqs[index] = rfq
            return rfq
        }
        return null
    }
}
