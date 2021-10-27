package com.grappim.sign_up

data class SignUpData(
    val phone: String = "",
    val email: String = "",
    val password: String = ""
) {
    companion object {
        fun empty(): SignUpData = SignUpData()
    }

    fun setPhone(newPhone: String): SignUpData =
        copy(phone = newPhone)

    fun setEmail(newEmail: String): SignUpData =
        copy(email = newEmail)

    fun setPassword(newPassword: String): SignUpData =
        copy(password = newPassword)
}