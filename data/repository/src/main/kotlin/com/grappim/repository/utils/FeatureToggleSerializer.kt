package com.grappim.repository.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.grappim.repository.model.datastore.FeatureToggleProto
import java.io.InputStream
import java.io.OutputStream

object FeatureToggleSerializer : Serializer<FeatureToggleProto> {
    override val defaultValue: FeatureToggleProto
        get() = FeatureToggleProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FeatureToggleProto {
        try {
            return FeatureToggleProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FeatureToggleProto, output: OutputStream) =
        t.writeTo(output)
}