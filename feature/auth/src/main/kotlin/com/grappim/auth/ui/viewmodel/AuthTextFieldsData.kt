package com.grappim.auth.ui.viewmodel

data class AuthTextFieldsData(
    val phone: String,
    val password: String,
    val isPhoneFullyEntered: Boolean
) {
    companion object {
        fun empty(): AuthTextFieldsData =
            AuthTextFieldsData(
                phone = "",
                password = "",
                isPhoneFullyEntered = false
            )
    }
}