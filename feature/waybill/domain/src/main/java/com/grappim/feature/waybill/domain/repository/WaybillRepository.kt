package com.grappim.feature.waybill.domain.repository

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.common.lce.VoidTry
import com.grappim.feature.waybill.domain.interactor.conductWaybill.ConductWaybillParams
import com.grappim.feature.waybill.domain.interactor.createWaybillProduct.CreateWaybillProductParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProducts.GetWaybillProductsParams
import com.grappim.feature.waybill.domain.interactor.rollbackWaybill.RollbackWaybillParams
import com.grappim.feature.waybill.domain.interactor.updateWaybillProduct.UpdateWaybillProductParams
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.model.WaybillProduct
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface WaybillRepository {

    fun getAcceptanceListPaging(): Flow<PagingData<Waybill>>
    suspend fun createDraftWaybill(): Try<Waybill, Throwable>

    fun getProducts(
        params: GetWaybillProductsParams
    ): Flow<PagingData<WaybillProduct>>

    suspend fun createWaybillProduct(
        params: CreateWaybillProductParams
    ): Try<BigDecimal, Throwable>

    suspend fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeParams
    ): Try<WaybillProduct, Throwable>

    suspend fun updateWaybillProduct(
        params: UpdateWaybillProductParams
    ): Try<BigDecimal, Throwable>

    suspend fun conductWaybill(
        params: ConductWaybillParams
    ): Try<Unit, Throwable>

    suspend fun rollbackWaybill(
        params: RollbackWaybillParams
    ): VoidTry<Throwable>
}
