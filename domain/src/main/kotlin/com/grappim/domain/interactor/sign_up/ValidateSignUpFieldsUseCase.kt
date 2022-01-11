package com.grappim.domain.interactor.sign_up

import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.model.sign_up.SignUpData
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ValidateSignUpFieldsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    data class Params(
        val signupData: SignUpData
    )

}