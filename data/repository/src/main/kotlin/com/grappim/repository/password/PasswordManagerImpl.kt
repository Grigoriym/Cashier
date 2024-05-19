package com.grappim.repository.password

import android.util.Base64
import com.grappim.common.di.AppScope
import com.grappim.domain.password.PasswordManager
import com.grappim.repository.BuildConfig
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@AppScope
class PasswordManagerImpl @Inject constructor() : PasswordManager {

    override fun encryptPassword(password: String): String {
        return AES256.encrypt(password, BuildConfig.cashier_secret_key)
    }

    private object AES256 {
        private const val AES_ALGORITHM = "AES"
        private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"

        @Suppress("MagicNumber")
        private fun cipher(
            encryptionMode: Int,
            secretKey: String
        ): Cipher {
            if (secretKey.length != 32) error("SecretKey length is not 32 chars")

            val cipher = Cipher.getInstance(TRANSFORMATION)
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), AES_ALGORITHM)
            val ivParams = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
            cipher.init(encryptionMode, secretKeySpec, ivParams)
            return cipher
        }

        fun encrypt(str: String, secretKey: String): String {
            val encrypted = cipher(
                encryptionMode = Cipher.ENCRYPT_MODE,
                secretKey = secretKey
            ).doFinal(str.toByteArray(Charsets.UTF_8))
            return String(Base64.encode(encrypted, Base64.DEFAULT))
        }

        fun decrypt(str: String, secretKey: String): String {
            val byteStr = Base64.decode(
                str.toByteArray(Charsets.UTF_8),
                Base64.DEFAULT
            )
            return String(cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(byteStr))
        }
    }
}
