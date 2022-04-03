package com.grappim.sign_up.domain.interactor

import com.grappim.common.asynchronous.usecase.SimpleCoroutineUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.sign_up.domain.model.SignUpData
import kotlinx.coroutines.CoroutineDispatcher
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateSignUpFieldsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SimpleCoroutineUseCase<ValidateSignUpFieldsUseCase.Params, ValidateSignUpFieldsUseCase.ValidationData?>(
    ioDispatcher
) {

    data class Params(
        val signupData: SignUpData
    )

    override suspend fun execute(parameters: Params): ValidationData? {
        val phoneValidation = validatePhone(parameters.signupData)
        val emailValidation = validateEmail(parameters.signupData)
        val passwordValidation = validatePassword(parameters.signupData)
        val repeatPasswordValidation = validateRepeatPassword(parameters.signupData)

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
            object Empty : ValidationTypes.Phone()
            object PhoneLength : ValidationTypes.Phone()
        }

        sealed class Email : ValidationTypes() {
            object Empty : ValidationTypes.Email()
            object CorrectEmail : ValidationTypes.Email()
        }

        sealed class Password : ValidationTypes() {
            object Empty : ValidationTypes.Password()
            object NotValid : ValidationTypes.Password()
        }

        sealed class RepeatPassword : ValidationTypes() {
            object NotEqual : ValidationTypes.RepeatPassword()
            object Empty : ValidationTypes.RepeatPassword()
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

    private fun validatePassword(signupData: SignUpData): ValidationTypes.Password? =
        when {
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
        val isValidLength = password.length >= 6
        val containsDigit = password.toCharArray().any {
            it.isDigit()
        }
        val containsUppercase = password.toCharArray().any{
            it.isUpperCase()
        }
        return !isValidLength && !containsDigit && !containsUppercase
    }


    private fun validateEmail(signupData: SignUpData): ValidationTypes.Email? =
        when {
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

    private fun validatePhone(signupData: SignUpData): ValidationTypes.Phone? =
        when {
            signupData.phone.isEmpty() -> {
                ValidationTypes.Phone.Empty
            }
            signupData.phone.length != 10 -> {
                ValidationTypes.Phone.PhoneLength
            }
            else -> {
                null
            }
        }

    private fun String.isEmailNotValid(): Boolean =
        !emailPattern.matcher(this).matches()

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