package com.grappim.domain.interactor.waybill

import androidx.paging.PagingData
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillProductsUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : GetWaybillProductsUseCase {

    override fun execute(
        params: GetWaybillProductsParams
    ): Flow<PagingData<WaybillProduct>> =
        waybillRepository.getProducts(params)
}