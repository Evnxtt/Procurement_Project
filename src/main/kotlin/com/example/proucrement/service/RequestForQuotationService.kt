package com.example.procurement.service

import com.example.procurement.model.RequestForQuotation
import com.example.procurement.repository.RepositoryProvider

class RequestForQuotationService {
    private val repo = RepositoryProvider.rfqRepo

    fun createRFQ(rfq: RequestForQuotation): RequestForQuotation =
        repo.add(rfq.copy(status = "SENT"))

    fun getAllRFQs(): List<RequestForQuotation> = repo.getAll()

    fun updateStatus(id: Int, status: String): RequestForQuotation? {
        val rfq = repo.findById(id) ?: return null
        val updated = rfq.copy(status = status)
        return repo.update(updated)
    }
}
