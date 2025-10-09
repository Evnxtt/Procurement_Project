package com.example.procurement.model

import kotlinx.serialization.Serializable
import com.example.procurement.model.interfaces.IReceivable

@Serializable
data class GoodsReceiptNote(
    val id: Int,
    val purchaseOrderId: Int,
    val receivedItems: List<Item>,
    val receivedBy: User,
    var status: String = "Awaiting Receipt"
) : IReceivable {
    override fun receiveGoods(): Boolean {
        status = "Received"
        return true
    }
}
