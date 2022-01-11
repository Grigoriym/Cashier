package com.grappim.auth.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper,
    private val authScreenNavigator: AuthScreenNavigator
) : AuthViewModel() {

    init {
        clearData()
    }

    override val authFieldsData = MutableStateFlow(AuthTextFieldsData.empty())
    override val loginStatus = SingleLiveEvent<Try<Unit>>()

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

    @MainThread
    override fun goToRegisterFlow() {
        authScreenNavigator.goToSignUp()
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
                loginStatus.value = it
                when (it) {
                    is Try.Success -> {
                        workerHelper.startTokenRefresherWorker()
                        authScreenNavigator.goToSelectStock()
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