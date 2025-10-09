@file:UseSerializers(LocalDateSerializer::class)

package com.example.procurement.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import com.example.procurement.model.interfaces.IReceivable



object LocalDateSerializer : kotlinx.serialization.KSerializer<LocalDate> {
    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDate {
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
