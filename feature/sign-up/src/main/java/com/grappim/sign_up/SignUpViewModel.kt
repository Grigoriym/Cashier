package com.grappim.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.core.SingleLiveEvent
import com.grappim.core.utils.ResourceManager
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.sign_up.SignUpUseCase
import com.grappim.domain.model.sign_up.SignUpData
import com.grappim.domain.model.sign_up.SignUpFieldsValidationData
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val navigator: Navigator,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _signUpStatus = SingleLiveEvent<Try<Unit>>()
    val signUpStatus: LiveData<Try<Unit>>
        get() = _signUpStatus

    private val _signUpData = SingleLiveEvent<SignUpData>()
    val signUpData: LiveData<SignUpData>
        get() = _signUpData

    private val _signUpValidation = SingleLiveEvent<SignUpFieldsValidationData>()
    val signUpValidation: LiveData<SignUpFieldsValidationData>
        get() = _signUpValidation

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

            val validationData: SignUpFieldsValidationData? = validateData(currentData)
            if (validationData != null) {
                _signUpValidation.value = validationData!!
                return@launch
            }

            val phone = currentData.phone.trim()
            val email = currentData.email.trim()
            val password = currentData.password.trim()

            signUpUseCase.invoke(
                SignUpUseCase.Params(
                    phone = phone,
                    email = email,
                    password = password
                )
            ).collect {
                _signUpStatus.value = it
                when (it) {
                    is Try.Success -> {
                        navigator.navigateToFlow(NavigationFlow.RegisterToAuthFlow)
                    }
                }
            }
        }
    }

    private fun validateData(data: SignUpData): SignUpFieldsValidationData? {
        val isPhoneEmpty = data.phone.isEmpty()
        val isEmailEmpty = data.email.isEmpty()
        val isPasswordEmpty = data.email.isEmpty()
        if (isPhoneEmpty || isEmailEmpty || isPasswordEmpty) {
            return getErrorOnEmptyFields(
                isPhoneEmpty = isPhoneEmpty,
                isEmailEmpty = isEmailEmpty,
                isPasswordEmpty = isPasswordEmpty
            )
        } else {
            return null
        }
    }

    private fun getErrorOnEmptyFields(
        isPhoneEmpty: Boolean,
        isEmailEmpty: Boolean,
        isPasswordEmpty: Boolean
    ): SignUpFieldsValidationData =
        SignUpFieldsValidationData(
            phoneNumberErrorText = if (isPhoneEmpty) {
                resourceManager.getString(R.string.sign_up_phone_empty)
            } else {
                null
            },
            emailErrorText = if (isEmailEmpty) {
                resourceManager.getString(R.string.sign_up_email_empty)
            } else {
                null
            },
            passwordErrorText = if (isPasswordEmpty) {
                resourceManager.getString(R.string.sign_up_password_empty)
            } else {
                null
            }
        )
}