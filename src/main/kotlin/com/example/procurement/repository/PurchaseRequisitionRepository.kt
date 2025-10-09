package com.example.procurement.repository

import com.example.procurement.model.PurchaseRequisition

class PurchaseRequisitionRepository {
    private val requisitions = mutableListOf<PurchaseRequisition>()
    private var nextId = 1

    fun add(req: PurchaseRequisition): PurchaseRequisition {
        val withId = req.copy(id = nextId++)
        requisitions.add(withId)
        return withId
    }

    fun getAll(): List<PurchaseRequisition> = requisitions

    fun findById(id: Int): PurchaseRequisition? = requisitions.find { it.id == id }

    fun update(req: PurchaseRequisition): PurchaseRequisition? {
        val index = requisitions.indexOfFirst { it.id == req.id }
        if (index != -1) {
            requisitions[index] = req
            return req
        }
        return null
    }
}
