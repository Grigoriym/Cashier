package com.grappim.sign_up_presentation.model

import com.grappim.core.resources.NativeText

data class SignUpFieldsValidationData(
    val phoneNumberErrorText: NativeText? = null,
    val emailErrorText: NativeText? = null,
    val passwordErrorText: NativeText? = null
)