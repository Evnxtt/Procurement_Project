package com.example.procurement.service

import com.example.procurement.model.PurchaseOrder
import com.example.procurement.repository.RepositoryProvider

class PurchaseOrderService {
    private val repo = RepositoryProvider.orderRepo

    fun createOrder(order: PurchaseOrder): PurchaseOrder =
        repo.add(order.copy(status = "CREATED"))

    fun getAllOrders(): List<PurchaseOrder> = repo.getAll()

    fun approveOrder(id: Int): PurchaseOrder? {
        val order = repo.findById(id) ?: return null
        val updated = order.copy(status = "APPROVED")
        return repo.update(updated)
    }

    fun rejectOrder(id: Int, reason: String): PurchaseOrder? {
        val order = repo.findById(id) ?: return null
        val updated = order.copy(status = "REJECTED", remarks = reason)
        return repo.update(updated)
    }
}
