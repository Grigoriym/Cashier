package com.grappim.signup.presentation.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.signup.domain.model.SignUpData
import com.grappim.signup.presentation.model.SignUpFieldsValidationData
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
