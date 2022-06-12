package com.grappim.utils.biometric

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EncryptedMessage(
    val cipherText: ByteArray,
    val initializationVector: ByteArray,
    val savedAt: Long = System.currentTimeMillis()
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedMessage

        if (!cipherText.contentEquals(other.cipherText)) return false
        if (!initializationVector.contentEquals(other.initializationVector)) return false
        if (savedAt != other.savedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cipherText.contentHashCode()
        result = 31 * result + initializationVector.contentHashCode()
        result = 31 * result + savedAt.hashCode()
        return result
    }
}
