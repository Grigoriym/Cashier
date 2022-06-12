package com.grappim.sign_up_presentation.helper

import com.grappim.core.resources.NativeText
import com.grappim.sign_up.domain.interactor.ValidateSignUpFieldsUseCaseImpl
import com.grappim.sign_up_presentation.R
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData
import javax.inject.Inject

class FieldsValidatorHelper @Inject constructor(

) {

    fun getMessageFromValidationData(
        validationData: ValidateSignUpFieldsUseCaseImpl.ValidationData
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
        type: ValidateSignUpFieldsUseCaseImpl.ValidationTypes.RepeatPassword?
    ): NativeText? =
        when (type) {
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.RepeatPassword.NotEqual -> {
                NativeText.Resource(R.string.sign_up_repeat_password_not_equals)
            }
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.RepeatPassword.Empty -> {
                NativeText.Resource(R.string.sign_up_password_empty)
            }
            else -> {
                null
            }
        }

    private fun getPasswordError(type: ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Password?): NativeText? =
        when (type) {
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Password.Empty -> {
                NativeText.Resource(R.string.sign_up_password_empty)
            }
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Password.NotValid -> {
                NativeText.Resource(R.string.sign_up_password_requirements)
            }
            else -> {
                null
            }
        }

    private fun getEmailError(type: ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Email?): NativeText? =
        when (type) {
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Email.Empty -> {
                NativeText.Resource(R.string.sign_up_email_empty)
            }
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Email.CorrectEmail -> {
                NativeText.Resource(R.string.sign_up_email_validity)
            }
            else -> {
                null
            }
        }

    private fun getPhoneError(type: ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Phone?): NativeText? =
        when (type) {
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Phone.Empty -> {
                NativeText.Resource(R.string.sign_up_phone_empty)
            }
            ValidateSignUpFieldsUseCaseImpl.ValidationTypes.Phone.PhoneLength -> {
                NativeText.Resource(R.string.sign_up_phone_size)
            }
            else -> {
                null
            }
        }
}