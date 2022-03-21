package com.grappim.auth.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.auth.model.AuthTextFieldsData
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper
) : AuthViewModel() {

    init {
        clearData()
    }

    override val authFieldsData = MutableStateFlow(AuthTextFieldsData.empty())

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