package com.grappim.cashier.ui.auth

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.core.platform.SingleLiveEvent
import com.grappim.cashier.data.workers.WorkerHelper
import com.grappim.cashier.domain.login.LoginUseCase
import com.grappim.cashier.domain.repository.GeneralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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
        login(
            "7023335353",
            "qwe"
        )
    }

    private val _password = MutableLiveData<String>()
    val isPasswordNotBlank: LiveData<Boolean>
        get() = Transformations.map(_password) {
            it.isNotBlank()
        }

    private val _isFullPhoneNumberEntered = MutableLiveData<Boolean>()
    val isFullPhoneNumberEntered: LiveData<Boolean>
        get() = _isFullPhoneNumberEntered

    private val _loginStatus = SingleLiveEvent<Resource<Unit>>()
    val loginStatus: LiveData<Resource<Unit>>
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

    private fun clearData() {
        viewModelScope.launch {
            generalRepository.clearData()
        }
    }

}