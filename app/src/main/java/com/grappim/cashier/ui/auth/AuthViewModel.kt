package com.grappim.cashier.ui.auth

import androidx.annotation.MainThread
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.data.workers.WorkerHelper
import com.grappim.cashier.domain.login.LoginUseCase
import com.grappim.cashier.domain.repository.GeneralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _password = MutableLiveData<String>()
    val isPasswordNotBlank: LiveData<Boolean>
        get() = _password.map {
            it.isNotBlank()
        }

    private val _isFullPhoneNumberEntered = MutableLiveData<Boolean>()
    val isFullPhoneNumberEntered: LiveData<Boolean>
        get() = _isFullPhoneNumberEntered

    private val _loginStatus = mutableStateOf<Resource<Unit>>(
        Resource.Empty
    )
    val loginStatus: State<Resource<Unit>>
        get() = _loginStatus

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    @MainThread
    fun onPasswordEntered(password: String) {
        _password.value = password
    }

    @MainThread
    fun onPhoneNumberEntered(value: Boolean) {
        _isFullPhoneNumberEntered.value = value
    }

    @MainThread
    fun setPhoneNumber(value: String) {
        _phoneNumber.value = value
    }

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
            _loginStatus.value = Resource.Loading
            loginUseCase.invoke(mobile, password)
                .onFailure {
                    _loginStatus.value = Resource.Error(it)
                }.onSuccess {
                    workerHelper.startTokenRefresherWorker()
                    _loginStatus.value = Resource.Success(it)
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