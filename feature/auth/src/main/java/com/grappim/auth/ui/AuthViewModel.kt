package com.grappim.auth.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grappim.core.BaseViewModel
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.navigation.directions.auth.AuthScreenNavigator
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper,
    private val authScreenNavigator: AuthScreenNavigator
) : BaseViewModel() {

    init {
        clearData()
    }

    private val _authFieldsData = MutableStateFlow(AuthTextFieldsData.empty())
    val authFieldsData: StateFlow<AuthTextFieldsData>
        get() = _authFieldsData.asStateFlow()

    private val _loginStatus = SingleLiveEvent<Try<Unit>>()
    val loginStatus: LiveData<Try<Unit>>
        get() = _loginStatus

    fun setPassword(text: String) {
        val oldValue = _authFieldsData.value
        _authFieldsData.value = oldValue.copy(password = text)
    }

    fun setPhone(text: String) {
        val oldValue = _authFieldsData.value
        _authFieldsData.value = oldValue.copy(
            phone = text,
            isPhoneFullyEntered = text.length == 10
        )
    }

    fun login() {
        login(
            mobile = authFieldsData.value.phone,
            password = authFieldsData.value.password
        )
    }

    fun loginFromIme() {
        if (authFieldsData.value.isPhoneFullyEntered) {
            login()
        }
    }

    @MainThread
    fun goToRegisterFlow() {
        authScreenNavigator.goToSignUp()
    }

    @MainThread
    fun login(
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
                _loginStatus.value = it
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