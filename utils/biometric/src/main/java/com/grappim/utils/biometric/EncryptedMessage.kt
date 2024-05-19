package com.grappim.utils.biometric

data class EncryptedMessage(
    val cipherText: ByteArray,
    val initializationVector: ByteArray,
    val savedAt: Long = System.currentTimeMillis()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EncryptedMessage) return false

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
