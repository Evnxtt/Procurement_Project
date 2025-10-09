package com.example.procurement.repository

import com.example.procurement.model.GoodsReceiptNote

class GoodsReceiptRepository {
    private val receipts = mutableListOf<GoodsReceiptNote>()
    private var nextId = 1

    fun add(note: GoodsReceiptNote): GoodsReceiptNote {
        val withId = note.copy(id = nextId++)
        receipts.add(withId)
        return withId
    }

    fun getAll(): List<GoodsReceiptNote> = receipts
}
