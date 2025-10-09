package com.example.procurement.service

import com.example.procurement.model.PurchaseRequisition
import com.example.procurement.repository.RepositoryProvider

class PurchaseRequisitionService {
    private val repo = RepositoryProvider.requisitionRepo

    fun createRequisition(req: PurchaseRequisition): PurchaseRequisition =
        repo.add(req.copy(status = "PENDING"))

    fun getAllRequisitions(): List<PurchaseRequisition> = repo.getAll()

    fun approveRequisition(id: Int): PurchaseRequisition? {
        val requisition = repo.findById(id) ?: return null
        val updated = requisition.copy(status = "APPROVED")
        return repo.update(updated)
    }

    fun rejectRequisition(id: Int, reason: String): PurchaseRequisition? {
        val requisition = repo.findById(id) ?: return null
        val updated = requisition.copy(status = "REJECTED", remarks = reason)
        return repo.update(updated)
    }
}
