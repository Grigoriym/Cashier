package com.grappim.feature.auth.presentation.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.data.workersapi.WorkerHelper
import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.domain.LoginParams
import com.grappim.feature.auth.domain.LoginUseCase
import com.grappim.feature.auth.presentation.model.AuthTextFieldsData
import com.grappim.feature.auth.presentation.model.BiometricsDialogClickState
import com.grappim.feature.auth.presentation.model.BiometricsState
import com.grappim.utils.biometric.BiometricPromptUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val workerHelper: WorkerHelper,
    private val generalStorage: GeneralStorage,
    private val biometricPromptUtils: BiometricPromptUtils
) : AuthViewModel() {

    init {
        viewModelScope.launch {
            generalStorage.setAuthErrorFlow(false)
        }
    }

    override val authFieldsData = MutableStateFlow(AuthTextFieldsData.empty())

    override val setFingerprintEvent = MutableSharedFlow<BiometricsState>()

    override val biometricsIntent = MutableSharedFlow<Intent>()

    override val biometricsStatus: Flow<BiometricsStatus?>
        get() = generalStorage.biometricsStatus

    override fun setPassword(text: String) {
        val oldValue = authFieldsData.value
        authFieldsData.value = oldValue.copy(password = text)
    }

    override fun setPhone(text: String) {
        val oldValue = authFieldsData.value
        authFieldsData.value = oldValue.copy(
            phone = text,
            isPhoneFullyEntered = text.length == 10
        )
    }

    override fun login() {
        login(
            phone = authFieldsData.value.phone,
            password = authFieldsData.value.password
        )
    }

    override fun loginFromIme() {
        if (authFieldsData.value.isPhoneFullyEntered) {
            login()
        }
    }

    override fun goToRegisterFlow() {
        flowRouter.goToSignUpFromSignIn()
    }

    override fun goToSettings() {
        flowRouter.goToSettings()
    }

    private fun login(phone: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = loginUseCase.login(
                LoginParams(
                    phone = phone,
                    password = password
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    authSuccess()
                }

                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    private fun doOnSuccess() {
        workerHelper.startTokenRefresherWorker()
        flowRouter.goToSelectInfo()
    }

    private fun showBiometricPrompt() {
        val biometricPrompt = biometricPromptUtils.createBiometricPrompt {
            doOnSuccess()
        }
        val promptInfo = biometricPromptUtils.createPromptInfo()
        biometricPrompt.authenticate(promptInfo)
    }

    override fun setDialogAnswer(answer: BiometricsDialogClickState) {
        viewModelScope.launch {
            setFingerprintEvent.emit(BiometricsState.ShowNothing)
            when (answer) {
                BiometricsDialogClickState.Positive -> {
                    generalStorage.setBiometricsStatus(BiometricsStatus.SET)
                    biometricPromptUtils.checkBiometricsAvailability(
                        doOnSuccess = {
                            showBiometricPrompt()
                        },
                        doOnError = {
                            _error.value = IllegalArgumentException("lol")
                        },
                        doOnNoneEnrolled = {
                            biometricsIntent.emit(it)
                        }
                    )
                }

                BiometricsDialogClickState.Negative -> {
                    generalStorage.setBiometricsStatus(BiometricsStatus.REFUSED)
                    doOnSuccess()
                }
            }
        }
    }

    private fun authSuccess() {
        viewModelScope.launch {
            setFingerprintEvent.emit(BiometricsState.ShowPrompt)
        }
    }
}
