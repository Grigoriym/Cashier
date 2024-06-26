package com.grappim.signup.domain.interactor.validate

import com.grappim.signup.domain.model.SignUpData
import java.util.regex.Pattern
import javax.inject.Inject

private const val PHONE_MAX_LENGTH = 10
private const val PASSWORD_MIN_LENGTH = 6

class ValidateSignUpFieldsUseCase @Inject constructor() {

    fun execute(params: ValidateFieldsParams): ValidationData? {
        val phoneValidation = validatePhone(params.signupData)
        val emailValidation = validateEmail(params.signupData)
        val passwordValidation = validatePassword(params.signupData)
        val repeatPasswordValidation = validateRepeatPassword(params.signupData)

        return if (phoneValidation == null &&
            emailValidation == null &&
            passwordValidation == null
        ) {
            null
        } else {
            ValidationData(
                phone = phoneValidation,
                email = emailValidation,
                password = passwordValidation,
                repeatPassword = repeatPasswordValidation
            )
        }
    }

    data class ValidationData(
        val phone: ValidationTypes.Phone?,
        val email: ValidationTypes.Email?,
        val password: ValidationTypes.Password?,
        val repeatPassword: ValidationTypes.RepeatPassword?
    )

    sealed class ValidationTypes {
        sealed class Phone : ValidationTypes() {
            object Empty : Phone()
            object PhoneLength : Phone()
        }

        sealed class Email : ValidationTypes() {
            object Empty : Email()
            object CorrectEmail : Email()
        }

        sealed class Password : ValidationTypes() {
            object Empty : Password()
            object NotValid : Password()
        }

        sealed class RepeatPassword : ValidationTypes() {
            object NotEqual : RepeatPassword()
            object Empty : RepeatPassword()
        }
    }

    private fun validateRepeatPassword(signupData: SignUpData): ValidationTypes.RepeatPassword? =
        when {
            signupData.repeatPassword != signupData.password -> {
                ValidationTypes.RepeatPassword.NotEqual
            }

            signupData.repeatPassword.isEmpty() -> {
                ValidationTypes.RepeatPassword.Empty
            }

            else -> {
                null
            }
        }

    private fun validatePassword(signupData: SignUpData): ValidationTypes.Password? = when {
        signupData.password.isEmpty() -> {
            ValidationTypes.Password.Empty
        }

        isNotPasswordValid(signupData.password) -> {
            ValidationTypes.Password.NotValid
        }

        else -> {
            null
        }
    }

    private fun isNotPasswordValid(password: String): Boolean {
        val isValidLength = password.length >= PASSWORD_MIN_LENGTH
        val containsDigit = password.toCharArray().any {
            it.isDigit()
        }
        val containsUppercase = password.toCharArray().any {
            it.isUpperCase()
        }
        return !isValidLength && !containsDigit && !containsUppercase
    }

    private fun validateEmail(signupData: SignUpData): ValidationTypes.Email? = when {
        signupData.email.isEmpty() -> {
            ValidationTypes.Email.Empty
        }

        signupData.email.isEmailNotValid() -> {
            ValidationTypes.Email.CorrectEmail
        }

        else -> {
            null
        }
    }

    private fun validatePhone(signupData: SignUpData): ValidationTypes.Phone? = when {
        signupData.phone.isEmpty() -> {
            ValidationTypes.Phone.Empty
        }

        signupData.phone.length != PHONE_MAX_LENGTH -> {
            ValidationTypes.Phone.PhoneLength
        }

        else -> {
            null
        }
    }

    private fun String.isEmailNotValid(): Boolean = !emailPattern.matcher(this).matches()

    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    )
}
