package com.grappim.auth.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiometricUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun hasBiometricCapability(): Boolean {
        val canAuthenticate = BiometricManager
            .from(context)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)

        return canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun setBioMetricPrompt(
        title: String,
        subtitle: String,
        description: String,
        allowDeviceCredential: Boolean
    ): BiometricPrompt.PromptInfo {
        val builder = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setDescription(description)

        builder.apply {
            if (allowDeviceCredential) {
                setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            } else {
                setNegativeButtonText("Cancel")
            }
        }
        return builder.build()
    }

    fun initBiometricPrompt(){

    }

}