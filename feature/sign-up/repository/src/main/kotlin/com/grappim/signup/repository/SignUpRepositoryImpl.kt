package com.grappim.signup.repository

import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.common.lce.VoidTry
import com.grappim.cashier.common.lce.runOperationCatching
import com.grappim.domain.password.PasswordManager
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.feature.auth.network.models.signup.SignUpDTO
import com.grappim.signup.domain.interactor.signup.SignUpParams
import com.grappim.signup.domain.repository.SignUpRepository
import javax.inject.Inject

@FeatureScope
class SignUpRepositoryImpl @Inject constructor(
    @QualifierAuthApi private val authApi: AuthApi,
    private val passwordManager: PasswordManager
) : SignUpRepository {

    override suspend fun signUp(params: SignUpParams): VoidTry<Throwable> = runOperationCatching {
        val encryptedPassword = passwordManager.encryptPassword(params.password)
        val requestBody = SignUpDTO(
            phone = params.phone,
            email = params.email,
            password = encryptedPassword
        )
        authApi.signUp(requestBody)
    }
}
