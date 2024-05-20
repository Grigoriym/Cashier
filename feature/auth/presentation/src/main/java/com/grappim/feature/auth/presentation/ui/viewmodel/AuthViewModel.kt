package com.grappim.feature.auth.presentation.ui.viewmodel

import android.content.Intent
import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.feature.auth.presentation.model.AuthTextFieldsData
import com.grappim.feature.auth.presentation.model.BiometricsDialogClickState
import com.grappim.feature.auth.presentation.model.BiometricsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class AuthViewModel : BaseViewModel() {

    abstract val authFieldsData: StateFlow<AuthTextFieldsData>
    abstract val setFingerprintEvent: SharedFlow<BiometricsState>
    abstract val biometricsIntent: SharedFlow<Intent>
    abstract val biometricsStatus: Flow<BiometricsStatus?>

    abstract fun login()
    abstract fun setDialogAnswer(answer: BiometricsDialogClickState)

    abstract fun goToRegisterFlow()
    abstract fun loginFromIme()
    abstract fun goToSettings()
    abstract fun setPhone(text: String)
    abstract fun setPassword(text: String)
}
