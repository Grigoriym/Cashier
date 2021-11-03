package com.grappim.auth

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.GeneralRepository
import com.grappim.myapplication.WorkerHelper
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val generalRepository: GeneralRepository,
    private val workerHelper: WorkerHelper,
    private val navigator: Navigator
) : ViewModel() {

    init {
        clearData()
    }

    private val _loginStatus = SingleLiveEvent<Result<Unit>>()
    val loginStatus: LiveData<Result<Unit>>
        get() = _loginStatus

    fun login(authTextFieldsData: AuthTextFieldsData) {
        login(
            mobile = authTextFieldsData.phone,
            password = authTextFieldsData.password
        )
    }

    @MainThread
    fun goToRegisterFlow() {
        navigator.navigateToFlow(NavigationFlow.RegisterFlow)
    }

    @MainThread
    fun goToSelectStockFlow() {
        navigator.navigateToFlow(NavigationFlow.SelectInfoStockFlow)
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
                if (it is Result.Success) {
                    workerHelper.startTokenRefresherWorker()
                }
                _loginStatus.value = it
            }
        }
    }

    private fun clearData() {
        viewModelScope.launch {
            generalRepository.clearData()
        }
    }

}