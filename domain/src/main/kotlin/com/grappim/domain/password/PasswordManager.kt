package com.grappim.domain.password

interface PasswordManager {
    fun encryptPassword(password: String): String
}