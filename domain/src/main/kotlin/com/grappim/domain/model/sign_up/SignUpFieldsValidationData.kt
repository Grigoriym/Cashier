package com.grappim.domain.model.sign_up

data class SignUpFieldsValidationData(
    val phoneNumberErrorText: String? = null,
    val emailErrorText: String? = null,
    val passwordErrorText: String? = null
)