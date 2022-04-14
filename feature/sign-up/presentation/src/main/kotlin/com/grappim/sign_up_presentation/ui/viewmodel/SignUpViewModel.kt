package com.grappim.sign_up_presentation.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.sign_up.domain.model.SignUpData
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData
import kotlinx.coroutines.flow.StateFlow

abstract class SignUpViewModel : BaseViewModel() {

    abstract val signUpData: StateFlow<SignUpData>

    abstract val signUpValidation: StateFlow<SignUpFieldsValidationData>

    abstract fun setPhone(newPhone: String)

    abstract fun setPassword(newPassword: String)
    abstract fun setRepeatPassword(newPassword: String)

    abstract fun setEmail(newEmail: String)

    abstract fun signUpClicked()
}