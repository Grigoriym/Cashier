package com.grappim.sign_up.domain.interactor

interface ValidateSignUpFieldsUseCase {
    suspend fun execute(params: ValidateFieldsParams): ValidateSignUpFieldsUseCaseImpl.ValidationData?
}