package com.grappim.auth.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.auth.model.AuthTextFieldsData
import com.grappim.auth.model.DevSnackbar
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper,
    private val generalStorage: GeneralStorage
) : AuthViewModel() {

    init {
        viewModelScope.launch {
            generalStorage.setAuthErrorFlow(false)
        }
        clearData()
    }

    override val authFieldsData = MutableStateFlow(AuthTextFieldsData.empty())

    override val showDevSnackbar = SingleLiveEvent<DevSnackbar>()

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
        when (counter) {
            in 7..9 -> {
                showDevSnackbar.value = DevSnackbar.firstPhase(counter)
            }
            10 -> {
                showDevSnackbar.value = DevSnackbar.secondPhase()
            }
            else -> {
                showDevSnackbar.value = DevSnackbar.default()
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
                        workerHelper.startTokenRefresherWorker()
                        flowRouter.goToSelectInfo()
                    }
                    is Try.Error -> {
                        _error.value = it.exception
                    }
                }
            }
        }
    }

    private fun clearData() {
        viewModelScope.launch {
            generalRepository.clearData()
        }
    }

}