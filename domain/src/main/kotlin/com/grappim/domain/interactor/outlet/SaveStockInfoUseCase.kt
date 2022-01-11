package com.grappim.domain.interactor.outlet

import com.grappim.common.asynchronous.CoroutineUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.SelectInfoRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveStockInfoUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val selectInfoRemoteRepository: SelectInfoRemoteRepository
) : CoroutineUseCase<SaveStockInfoUseCase.Params, NoParams>(ioDispatcher) {

    data class Params(
        val stock: Stock
    )

    override suspend fun execute(parameters: Params): NoParams =
        selectInfoRemoteRepository.saveStock(parameters)
}