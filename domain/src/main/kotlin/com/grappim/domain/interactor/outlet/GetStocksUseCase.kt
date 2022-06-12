package com.grappim.domain.interactor.outlet

import com.grappim.common.lce.Try
import com.grappim.domain.model.outlet.Stock

interface GetStocksUseCase {

    suspend fun execute(): Try<List<Stock>, Throwable>

}