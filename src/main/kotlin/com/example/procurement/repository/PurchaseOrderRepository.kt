package com.example.procurement.repository

import com.example.procurement.model.PurchaseOrder

class PurchaseOrderRepository {
    private val orders = mutableListOf<PurchaseOrder>()
    private var nextId = 1

    fun add(order: PurchaseOrder): PurchaseOrder {
        val withId = order.copy(id = nextId++)
        orders.add(withId)
        return withId
    }

    fun getAll(): List<PurchaseOrder> = orders

    fun findById(id: Int): PurchaseOrder? = orders.find { it.id == id }

    fun update(order: PurchaseOrder): PurchaseOrder? {
        val index = orders.indexOfFirst { it.id == order.id }
        if (index != -1) {
            orders[index] = order
            return order
        }
        return null
    }
}
