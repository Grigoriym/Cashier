package com.grappim.signup.presentation.model

import com.grappim.cashier.core.resources.NativeText

data class SignUpFieldsValidationData(
    val phoneNumberErrorText: NativeText? = null,
    val emailErrorText: NativeText? = null,
    val passwordErrorText: NativeText? = null,
    val repeatPasswordErrorText: NativeText? = null
) {
    companion object {
        fun empty() = SignUpFieldsValidationData(
            phoneNumberErrorText = null,
            emailErrorText = null,
            passwordErrorText = null,
            repeatPasswordErrorText = null
        )
    }
}
