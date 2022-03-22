package com.grappim.auth.ui.viewmodel

import com.grappim.auth.model.AuthTextFieldsData
import com.grappim.core.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class AuthViewModel : BaseViewModel() {

    abstract val authFieldsData: StateFlow<AuthTextFieldsData>

    abstract fun login()

    abstract fun login(
        mobile: String,
        password: String
    )

    abstract fun goToRegisterFlow()
    abstract fun loginFromIme()
    abstract fun goToSettings()
    abstract fun setPhone(text: String)
    abstract fun setPassword(text: String)

}