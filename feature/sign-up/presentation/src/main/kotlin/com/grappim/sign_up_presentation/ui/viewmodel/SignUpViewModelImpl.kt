package com.grappim.sign_up_presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.sign_up.domain.interactor.SignUpUseCase
import com.grappim.sign_up.domain.interactor.ValidateSignUpFieldsUseCase
import com.grappim.sign_up.domain.model.SignUpData
import com.grappim.sign_up_presentation.helper.FieldsValidatorHelper
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModelImpl @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateSignUpFieldsUseCase: ValidateSignUpFieldsUseCase,
    private val fieldsValidatorHelper: FieldsValidatorHelper
) : SignUpViewModel() {

    override val signUpData = SingleLiveEvent<SignUpData>()

    override val signUpValidation = SingleLiveEvent<SignUpFieldsValidationData>()

    override fun setPhone(newPhone: String) {
        val oldData = signUpData.value ?: SignUpData.empty()
        signUpData.value = oldData.setPhone(newPhone)
        clearValidation()
    }

    override fun setPassword(newPassword: String) {
        val oldData = signUpData.value ?: SignUpData.empty()
        signUpData.value = oldData.setPassword(newPassword)
        clearValidation()
    }

    override fun setEmail(newEmail: String) {
        val oldData = signUpData.value ?: SignUpData.empty()
        signUpData.value = oldData.setEmail(newEmail)
        clearValidation()
    }

    private fun clearValidation() {
        signUpValidation.value = SignUpFieldsValidationData()
    }

    private suspend fun validateData(): ValidateSignUpFieldsUseCase.ValidationData? {
        val currentData = signUpData.value ?: SignUpData.empty()

        val validationData = validateSignUpFieldsUseCase.invoke(
            ValidateSignUpFieldsUseCase.Params(currentData)
        )
        return validationData
    }

    private suspend fun signUp() {
        val currentData = signUpData.value ?: SignUpData.empty()
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
            _loading.value = it is Try.Loading
            when (it) {
                is Try.Success -> {
                    onBackPressed()
                }
                is Try.Error -> {
                    _error.value = it.exception
                }
            }
        }
    }

    override fun signUpClicked() {
        viewModelScope.launch {
            val validationData = validateData()
            if (validationData != null) {
                signUpValidation.value = fieldsValidatorHelper
                    .getMessageFromValidationData(validationData)
            } else {
                signUp()
            }
        }
    }
}