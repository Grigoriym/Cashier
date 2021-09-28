package com.grappim.domain.repository

import androidx.paging.PagingData
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.*
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface WaybillRepository {

    fun getAcceptanceListPaging(): Flow<PagingData<Waybill>>
    fun createDraftWaybill(): Flow<Result<Waybill>>

    fun getProducts(
        params: GetWaybillProductsUseCase.Params
    ): Flow<PagingData<WaybillProduct>>

    fun createWaybillProduct(
        params: CreateWaybillProductUseCase.Params
    ): Flow<Result<BigDecimal>>

    fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.Params
    ): Flow<Result<WaybillProduct>>

    fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.Params
    ): Flow<Result<Product>>

    fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.Params
    ): Flow<Result<BigDecimal>>

    fun conductWaybill(
        params: ConductWaybillUseCase.Params
    ): Flow<Result<Waybill>>

    fun rollbackWaybill(
        params: RollbackWaybillUseCase.Params
    ): Flow<Result<Waybill>>

}