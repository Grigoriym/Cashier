package com.grappim.domain.interactor.products

import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.GeneralRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByQueryUseCaseImpl @Inject constructor(
    private val generalRepository: GeneralRepository
) : GetProductsByQueryUseCase {

    override fun execute(params: GetProductsByQueryParams): Flow<List<Product>> =
        generalRepository.getProductsByQuery(params)
}