package com.example.procurement.service

import com.example.procurement.model.GoodsReceiptNote
import com.example.procurement.repository.RepositoryProvider

class GoodsReceiptService {
    private val repo = RepositoryProvider.goodsReceiptRepo

    fun receiveGoods(note: GoodsReceiptNote): GoodsReceiptNote =
        repo.add(note.copy(receivedDate = note.receivedDate ?: java.time.LocalDate.now()))

    fun getAllReceipts(): List<GoodsReceiptNote> = repo.getAll()
}
