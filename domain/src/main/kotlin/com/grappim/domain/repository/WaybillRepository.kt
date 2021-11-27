package com.grappim.domain.repository

import androidx.paging.PagingData
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.*
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface WaybillRepository {

    fun getAcceptanceListPaging(): Flow<PagingData<Waybill>>
    fun createDraftWaybill(): Flow<Try<Waybill>>

    fun getProducts(
        params: GetWaybillProductsUseCase.Params
    ): Flow<PagingData<WaybillProduct>>

    fun createWaybillProduct(
        params: CreateWaybillProductUseCase.Params
    ): Flow<Try<BigDecimal>>

    fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.Params
    ): Flow<Try<WaybillProduct>>

    fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.Params
    ): Flow<Try<Product>>

    fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.Params
    ): Flow<Try<BigDecimal>>

    fun conductWaybill(
        params: ConductWaybillUseCase.Params
    ): Flow<Try<Waybill>>

    fun rollbackWaybill(
        params: RollbackWaybillUseCase.Params
    ): Flow<Try<Waybill>>

}