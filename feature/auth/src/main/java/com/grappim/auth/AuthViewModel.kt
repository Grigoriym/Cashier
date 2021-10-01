package com.grappim.auth

import androidx.annotation.MainThread
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.myapplication.WorkerHelper
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

    private val _loginStatus = mutableStateOf<Result<Unit>>(
        Result.Empty
    )
    val loginStatus: State<Result<Unit>>
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
                LoginUseCase.Params(
                    mobile = mobile,
                    password = password
                )
            ).collect {
                if (it is Result.Success) {
                    workerHelper.startTokenRefresherWorker()
                }
                _loginStatus.value = it
            }
        }
    }

    fun loginStatusDuctTape() {
        _loginStatus.value = Result.Empty
    }

    private fun clearData() {
        viewModelScope.launch {
            generalRepository.clearData()
        }
    }

}