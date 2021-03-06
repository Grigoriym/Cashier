package com.grappim.sign_up_repository

import com.grappim.common.asynchronous.runOperationCatching
import com.grappim.common.di.FeatureScope
import com.grappim.common.lce.VoidTry
import com.grappim.domain.password.PasswordManager
import com.grappim.feature.auth.data_network.api.AuthApi
import com.grappim.feature.auth.data_network.di.QualifierAuthApi
import com.grappim.feature.auth.data_network.models.sign_up.SignUpDTO
import com.grappim.sign_up.domain.interactor.sign_up.SignUpParams
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
