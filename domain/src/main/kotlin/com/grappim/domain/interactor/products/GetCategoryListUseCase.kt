package com.grappim.domain.interactor.products

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Category
import com.grappim.domain.repository.GeneralRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val generalRepository: GeneralRepository
) : FlowUseCase<GetCategoryListUseCase.Params, List<Category>>(ioDispatcher) {

    data class Params(
        val sendDefaultCategory: Boolean = true
    )

    override fun execute(params: Params): Flow<Try<List<Category>>> =
        generalRepository.getCategories(params)

    fun execute2(params: Params): Flow<List<Category>> =
        generalRepository.getCategories2(params)
}