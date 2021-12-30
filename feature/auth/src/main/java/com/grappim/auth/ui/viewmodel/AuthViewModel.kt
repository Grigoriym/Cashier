package com.grappim.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import com.grappim.core.BaseViewModel
import com.grappim.domain.base.Try
import kotlinx.coroutines.flow.StateFlow

abstract class AuthViewModel : BaseViewModel() {

    abstract val loginStatus: LiveData<Try<Unit>>
    abstract val authFieldsData: StateFlow<AuthTextFieldsData>

    abstract fun login()

    abstract fun login(
        mobile: String,
        password: String
    )

    abstract fun goToRegisterFlow()
    abstract fun loginFromIme()
    abstract fun setPhone(text: String)
    abstract fun setPassword(text: String)

}