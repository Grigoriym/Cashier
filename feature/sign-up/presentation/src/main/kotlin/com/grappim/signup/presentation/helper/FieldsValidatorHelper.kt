package com.grappim.signup.presentation.helper

import com.grappim.cashier.core.resources.NativeText
import com.grappim.signup.domain.interactor.validate.ValidateSignUpFieldsUseCase
import com.grappim.signup.presentation.model.SignUpFieldsValidationData
import com.grappim.uikit.R
import javax.inject.Inject

class FieldsValidatorHelper @Inject constructor() {

    fun getMessageFromValidationData(
        validationData: ValidateSignUpFieldsUseCase.ValidationData
    ): SignUpFieldsValidationData {
        val phoneError: NativeText? = getPhoneError(validationData.phone)
        val emailError: NativeText? = getEmailError(validationData.email)
        val passwordError: NativeText? = getPasswordError(validationData.password)
        val repeatPasswordError: NativeText? = getRepeatPasswordError(validationData.repeatPassword)

        return SignUpFieldsValidationData(
            phoneNumberErrorText = phoneError,
            emailErrorText = emailError,
            passwordErrorText = passwordError,
            repeatPasswordErrorText = repeatPasswordError
        )
    }

    private fun getRepeatPasswordError(
        type: ValidateSignUpFieldsUseCase.ValidationTypes.RepeatPassword?
    ): NativeText? = when (type) {
        ValidateSignUpFieldsUseCase.ValidationTypes.RepeatPassword.NotEqual -> {
            NativeText.Resource(R.string.sign_up_repeat_password_not_equals)
        }

        ValidateSignUpFieldsUseCase.ValidationTypes.RepeatPassword.Empty -> {
            NativeText.Resource(R.string.sign_up_password_empty)
        }

        else -> {
            null
        }
    }

    private fun getPasswordError(
        type: ValidateSignUpFieldsUseCase.ValidationTypes.Password?
    ): NativeText? = when (type) {
        ValidateSignUpFieldsUseCase.ValidationTypes.Password.Empty -> {
            NativeText.Resource(R.string.sign_up_password_empty)
        }

        ValidateSignUpFieldsUseCase.ValidationTypes.Password.NotValid -> {
            NativeText.Resource(R.string.sign_up_password_requirements)
        }

        else -> {
            null
        }
    }

    private fun getEmailError(
        type: ValidateSignUpFieldsUseCase.ValidationTypes.Email?
    ): NativeText? = when (type) {
        ValidateSignUpFieldsUseCase.ValidationTypes.Email.Empty -> {
            NativeText.Resource(R.string.sign_up_email_empty)
        }

        ValidateSignUpFieldsUseCase.ValidationTypes.Email.CorrectEmail -> {
            NativeText.Resource(R.string.sign_up_email_validity)
        }

        else -> {
            null
        }
    }

    private fun getPhoneError(
        type: ValidateSignUpFieldsUseCase.ValidationTypes.Phone?
    ): NativeText? = when (type) {
        ValidateSignUpFieldsUseCase.ValidationTypes.Phone.Empty -> {
            NativeText.Resource(R.string.sign_up_phone_empty)
        }

        ValidateSignUpFieldsUseCase.ValidationTypes.Phone.PhoneLength -> {
            NativeText.Resource(R.string.sign_up_phone_size)
        }

        else -> {
            null
        }
    }
}
