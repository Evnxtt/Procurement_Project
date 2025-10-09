@file:UseSerializers(LocalDateSerializer::class)

package com.example.procurement.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

object LocalDateSerializer : kotlinx.serialization.KSerializer<LocalDate> {
    override val descriptor = kotlinx.serialization.descriptors.PrimitiveSerialDescriptor("LocalDate", kotlinx.serialization.encoding.PrimitiveKind.STRING)

    override fun serialize(encoder: kotlinx.serialization.encoding.Encoder, value: LocalDate) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: kotlinx.serialization.encoding.Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}

@Serializable
data class GoodsReceiptNote(
    val id: Int,
    val purchaseOrderId: Int,
    val receivedItems: List<Item>,
    val receivedBy: User,
    val receivedDate: LocalDate? = null,
    var status: String = "Awaiting Receipt"
) : IReceivable {
    override fun receiveGoods(): Boolean {
        status = "Received"
        return true
    }
}
