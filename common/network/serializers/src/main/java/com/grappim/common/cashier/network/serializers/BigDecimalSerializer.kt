package com.grappim.common.cashier.network.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

object BigDecimalSerializer : KSerializer<BigDecimal> {

    override fun deserialize(decoder: Decoder): BigDecimal {
        val string = decoder.decodeString()
        return BigDecimal(string)
    }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("decimal", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString())
    }
}
