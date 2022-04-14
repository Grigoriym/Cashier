package com.grappim.auth.ui.viewmodel

import android.content.Intent
import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.auth.biometric.BiometricPromptUtils
import com.grappim.auth.model.AuthTextFieldsData
import com.grappim.auth.model.BiometricsDialogClickState
import com.grappim.auth.model.BiometricsState
import com.grappim.auth.model.DevSnackbar
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.domain.storage.GeneralStorage
import com.grappim.workers.WorkerHelper
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

    override val showDevSnackbar = MutableSharedFlow<DevSnackbar>()
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
            mobile = authFieldsData.value.phone,
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

    override fun onLogoClick(counter: Int) {
        viewModelScope.launch {
            when (counter) {
                in 7..9 -> {
                    showDevSnackbar.emit(DevSnackbar.firstPhase(counter))
                }
                10 -> {
                    showDevSnackbar.emit(DevSnackbar.secondPhase())
                }
                else -> {
                    showDevSnackbar.emit(DevSnackbar.default())
                }
            }
        }
    }

    @MainThread
    override fun login(
        mobile: String,
        password: String
    ) {
        viewModelScope.launch {
            loginUseCase(
                LoginUseCase.Params(
                    phone = mobile,
                    password = password
                )
            ).collect {
                _loading.value = it is Try.Loading
                when (it) {
                    is Try.Success -> {
                        authSuccess()

                    }
                    is Try.Error -> {
                        _error.value = it.exception
                    }
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

    override fun enterGuestMode() {
        viewModelScope.launch {
            generalStorage.clearData()
        }
    }

    private fun authSuccess() {
        viewModelScope.launch {
            setFingerprintEvent.emit(BiometricsState.ShowPrompt)
        }
    }

}