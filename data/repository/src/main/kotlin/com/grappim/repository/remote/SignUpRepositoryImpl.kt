package com.grappim.repository.remote

import com.grappim.domain.base.Result
import com.grappim.domain.interactor.sign_up.SignUpUseCase
import com.grappim.domain.repository.SignUpRepository
import com.grappim.network.api.SignUpApi
import com.grappim.network.di.QualifierSignUpApi
import com.grappim.network.model.sign_up.SignUpDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpRepositoryImpl @Inject constructor(
    @QualifierSignUpApi private val signUpApi: SignUpApi
) : SignUpRepository {

    override fun signUp(
        params: SignUpUseCase.Params
    ): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            val requestBody = SignUpDTO(
                phone = params.phone,
                email = params.email,
                password = params.password
            )
            signUpApi.signUp(requestBody)
            emit(Result.Success(Unit))
        }
}
