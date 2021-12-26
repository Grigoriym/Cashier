package com.grappim.repository.remote

import com.grappim.di.AppScope
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.sign_up.SignUpUseCase
import com.grappim.domain.repository.SignUpRepository
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi
import com.grappim.network.model.sign_up.SignUpDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@AppScope
class SignUpRepositoryImpl @Inject constructor(
    @QualifierAuthApi private val authApi: AuthApi
) : SignUpRepository {

    override fun signUp(
        params: SignUpUseCase.Params
    ): Flow<Try<Unit>> =
        flow {
            emit(Try.Loading)
            val requestBody = SignUpDTO(
                phone = params.phone,
                email = params.email,
                password = params.password
            )
            authApi.signUp(requestBody)
            emit(Try.Success(Unit))
        }
}
