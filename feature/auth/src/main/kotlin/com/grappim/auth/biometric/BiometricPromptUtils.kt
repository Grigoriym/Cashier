package com.grappim.auth.biometric

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.grappim.auth.R
import com.grappim.common.di.ActivityContext
import com.grappim.common.di.ActivityScope
import com.grappim.logger.logD
import javax.inject.Inject

@ActivityScope
class BiometricPromptUtils @Inject constructor(
    @ActivityContext private val activityContext: Context,
    private val activity: AppCompatActivity
) {

    @Suppress("DEPRECATION")
    suspend fun checkBiometricsAvailability(
        doOnSuccess: () -> Unit,
        doOnError: () -> Unit,
        doOnNoneEnrolled: suspend (enrollIntent: Intent) -> Unit
    ) {
        val biometricManager = BiometricManager.from(activityContext)
        when (val answer = biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                logD("App can authenticate using biometrics")
                doOnSuccess()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val enrollIntent = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                        Intent(
                            Settings.ACTION_BIOMETRIC_ENROLL
                        ).apply {
                            putExtra(
                                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_WEAK
                            )
                        }
                    }
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                        Intent(Settings.ACTION_FINGERPRINT_ENROLL)
                    }
                    else -> {
                        Intent(Settings.ACTION_SECURITY_SETTINGS)
                    }
                }
                doOnNoneEnrolled(enrollIntent)
            }
            else -> {
                logD("Biometric features error because of $answer")
                doOnError()
            }
        }
    }

    fun createBiometricPrompt(
        processSuccess: (BiometricPrompt.AuthenticationResult) -> Unit
    ): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)

        val callback = object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errCode, errString)
                logD("errCode is $errCode and errString is: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                logD("User biometric rejected.")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                logD("Authentication was successful")
                processSuccess(result)
            }
        }
        return BiometricPrompt(activity, executor, callback)
    }

    fun createPromptInfo(): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder().apply {
            setTitle(activityContext.getString(R.string.prompt_info_title))
            setSubtitle(activityContext.getString(R.string.prompt_info_subtitle))
            setDescription(activityContext.getString(R.string.prompt_info_description))
            setConfirmationRequired(false)
            setNegativeButtonText(activityContext.getString(R.string.prompt_info_use_app_password))
        }.build()
}