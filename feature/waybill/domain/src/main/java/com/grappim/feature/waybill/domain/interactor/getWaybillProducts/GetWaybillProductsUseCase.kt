package com.grappim.feature.waybill.domain.interactor.getWaybillProducts

import androidx.paging.PagingData
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillProductsUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    fun execute(params: GetWaybillProductsParams): Flow<PagingData<WaybillProduct>> =
        waybillRepository.getProducts(params)
}
