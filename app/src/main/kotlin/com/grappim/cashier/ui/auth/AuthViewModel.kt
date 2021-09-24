package com.grappim.cashier.ui.auth

import androidx.annotation.MainThread
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.data.workers.WorkerHelper
import com.grappim.cashier.domain.login.LoginUseCase
import com.grappim.cashier.domain.repository.GeneralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper
) : ViewModel() {

    init {
        clearData()
    }

    private val _loginStatus = mutableStateOf<Resource<Unit>>(
        Resource.Empty
    )
    val loginStatus: State<Resource<Unit>>
        get() = _loginStatus

    fun login(authTextFieldsData: AuthTextFieldsData) {
        login(
            mobile = authTextFieldsData.phone,
            password = authTextFieldsData.password
        )
    }

    @MainThread
    fun login(
        mobile: String,
        password: String
    ) {
        viewModelScope.launch {
            loginUseCase(
                LoginUseCase.LoginRequestData(
                    mobile = mobile,
                    password = password
                )
            ).collect {
                if (it is Resource.Success) {
                    workerHelper.startTokenRefresherWorker()
                }
                _loginStatus.value = it
            }
        }
    }

    fun loginStatusDuctTape() {
        _loginStatus.value = Resource.Empty
    }

    private fun clearData() {
        viewModelScope.launch {
            generalRepository.clearData()
        }
    }

}