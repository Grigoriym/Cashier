package com.grappim.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.sign_up.SignUpUseCase
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _signUpStatus = SingleLiveEvent<Try<Unit>>()
    val signUpStatus: LiveData<Try<Unit>>
        get() = _signUpStatus

    private val _signUpData = SingleLiveEvent<SignUpData>()
    val signUpData: LiveData<SignUpData>
        get() = _signUpData

    fun setPhone(newPhone: String) {
        val oldData = _signUpData.value ?: SignUpData.empty()
        _signUpData.value = oldData.setPhone(newPhone)
    }

    fun setPassword(newPassword: String) {
        val oldData = _signUpData.value ?: SignUpData.empty()
        _signUpData.value = oldData.setPassword(newPassword)
    }

    fun setEmail(newEmail: String) {
        val oldData = _signUpData.value ?: SignUpData.empty()
        _signUpData.value = oldData.setEmail(newEmail)
    }

    fun signUp() {
        viewModelScope.launch {
            val currentData = _signUpData.value ?: SignUpData.empty()
            val phone = currentData.phone
            val email = currentData.email
            val password = currentData.password

            signUpUseCase.invoke(
                SignUpUseCase.Params(
                    phone = phone,
                    email = email,
                    password = password
                )
            ).collect {
                when (it) {
                    is Try.Success -> {
                        navigator.navigateToFlow(NavigationFlow.RegisterToAuthFlow)
                    }
                }
            }
        }
    }
}