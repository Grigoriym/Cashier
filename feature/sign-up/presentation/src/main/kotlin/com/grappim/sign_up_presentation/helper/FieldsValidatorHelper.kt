package com.grappim.sign_up_presentation.helper

import com.grappim.core.resources.NativeText
import com.grappim.sign_up.domain.interactor.ValidateSignUpFieldsUseCase
import com.grappim.sign_up_presentation.R
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData
import javax.inject.Inject

class FieldsValidatorHelper @Inject constructor(

) {

    fun getMessageFromValidationData(
        validationData: ValidateSignUpFieldsUseCase.ValidationData
    ): SignUpFieldsValidationData {
        val phoneError: NativeText? = getPhoneError(validationData.phone)
        val emailError: NativeText? = getEmailError(validationData.email)
        val passwordError: NativeText? = getPasswordError(validationData.password)

        return SignUpFieldsValidationData(
            phoneNumberErrorText = phoneError,
            emailErrorText = emailError,
            passwordErrorText = passwordError
        )
    }

    private fun getPasswordError(type: ValidateSignUpFieldsUseCase.ValidationTypes.Password?): NativeText? =
        when (type) {
            ValidateSignUpFieldsUseCase.ValidationTypes.Password.Empty -> {
                NativeText.Resource(R.string.sign_up_password_empty)
            }
            else -> {
                null
            }
        }

    private fun getEmailError(type: ValidateSignUpFieldsUseCase.ValidationTypes.Email?): NativeText? =
        when (type) {
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

    private fun getPhoneError(type: ValidateSignUpFieldsUseCase.ValidationTypes.Phone?): NativeText? =
        when (type) {
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