package com.grappim.sign_up_presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import com.grappim.common.lce.Try
import com.grappim.core.base.BaseViewModel
import com.grappim.core.base.BaseViewModel2
import com.grappim.sign_up.domain.model.SignUpData
import com.grappim.sign_up_presentation.model.SignUpFieldsValidationData

abstract class SignUpViewModel : BaseViewModel2() {

    abstract val signUpData: LiveData<SignUpData>

    abstract val signUpValidation: LiveData<SignUpFieldsValidationData>

    abstract fun setPhone(newPhone: String)

    abstract fun setPassword(newPassword: String)

    abstract fun setEmail(newEmail: String)

    abstract fun signUpClicked()
}