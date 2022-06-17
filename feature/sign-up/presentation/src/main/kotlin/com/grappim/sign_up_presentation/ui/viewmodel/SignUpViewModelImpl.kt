package com.grappim.sign_up_presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.sign_up.domain.interactor.sign_up.SignUpParams
import com.grappim.sign_up.domain.interactor.sign_up.SignUpUseCase
import com.grappim.sign_up.domain.interactor.validate.ValidateFieldsParams
import com.grappim.sign_up.domain.interactor.validate.ValidateSignUpFieldsUseCase
import com.grappim.sign_up.domain.model.SignUpData
import com.grappim.sign_up_presentation.helper.FieldsValidatorHelper
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModelImpl @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateSignUpFieldsUseCase: ValidateSignUpFieldsUseCase,
    private val fieldsValidatorHelper: FieldsValidatorHelper
) : SignUpViewModel() {

    override val signUpData = MutableStateFlow(SignUpData.empty())

    override val signUpValidation = MutableStateFlow(
        SignUpFieldsValidationData.empty()
    )

    override fun setPhone(newPhone: String) {
        val oldData = signUpData.value
        signUpData.value = oldData.setPhone(newPhone)
        clearValidation()
    }

    override fun setPassword(newPassword: String) {
        val oldData = signUpData.value
        signUpData.value = oldData.setPassword(newPassword)
        clearValidation()
    }

    override fun setRepeatPassword(newPassword: String) {
        val oldData = signUpData.value
        signUpData.value = oldData.setRepeatPassword(newPassword)
        clearValidation()
    }

    override fun setEmail(newEmail: String) {
        val oldData = signUpData.value
        signUpData.value = oldData.setEmail(newEmail)
        clearValidation()
    }

    private fun clearValidation() {
        signUpValidation.value = SignUpFieldsValidationData()
    }

    private suspend fun validateData(): ValidateSignUpFieldsUseCase.ValidationData? {
        val currentData = signUpData.value

        val validationData = validateSignUpFieldsUseCase.execute(
            ValidateFieldsParams(currentData)
        )
        return validationData
    }

    private suspend fun signUp() {
        val currentData = signUpData.value
        val phone = currentData.phone.trim()
        val email = currentData.email.trim()
        val password = currentData.password.trim()

        _loading.value = true
        val result = signUpUseCase.signUp(
            SignUpParams(
                phone = phone,
                email = email,
                password = password
            )
        )
        _loading.value = false
        when (result) {
            is Try.Success -> {
                onBackPressed()
            }
            is Try.Error -> {
                _error.value = result.result
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