package com.grappim.cashier.domain.waybill

import androidx.paging.PagingData
import com.grappim.cashier.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillProductsUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    fun getProducts(
        waybillId: Int
    ): Flow<PagingData<WaybillProduct>> =
        waybillRepository.getProducts(waybillId)
}