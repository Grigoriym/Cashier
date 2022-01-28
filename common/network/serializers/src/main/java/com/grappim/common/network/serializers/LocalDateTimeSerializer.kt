package com.grappim.common.network.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val millis = decoder.decodeLong()
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
    }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("date", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeLong(
            Instant.from(value.atZone(ZoneId.systemDefault())).toEpochMilli()
        )
    }
}