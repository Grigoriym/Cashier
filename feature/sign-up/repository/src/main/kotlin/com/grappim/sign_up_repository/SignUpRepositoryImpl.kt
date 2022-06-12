package com.grappim.sign_up_repository

import com.grappim.common.asynchronous.runOperationCatching
import com.grappim.common.di.FeatureScope
import com.grappim.common.lce.VoidTry
import com.grappim.domain.password.PasswordManager
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi
import com.grappim.network.model.sign_up.SignUpDTO
import com.grappim.sign_up.domain.interactor.SignUpParams
import com.grappim.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

@FeatureScope
class SignUpRepositoryImpl @Inject constructor(
    @QualifierAuthApi private val authApi: AuthApi,
    private val passwordManager: PasswordManager
) : SignUpRepository {

    override suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable> = runOperationCatching {
        val encryptedPassword = passwordManager.encryptPassword(params.password)
        val requestBody = SignUpDTO(
            phone = params.phone,
            email = params.email,
            password = encryptedPassword
        )
        authApi.signUp(requestBody)
    }
}
