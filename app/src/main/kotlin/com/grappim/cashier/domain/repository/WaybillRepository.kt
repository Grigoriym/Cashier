package com.grappim.cashier.domain.repository

import androidx.paging.PagingData
import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.data.remote.model.waybill.WaybillDTO
import com.grappim.cashier.domain.products.GetProductByBarcodeUseCase
import com.grappim.cashier.domain.waybill.CreateWaybillProductUseCase
import com.grappim.cashier.domain.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.cashier.domain.waybill.UpdateWaybillProductUseCase
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.domain.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface WaybillRepository {

    fun getAcceptanceListPaging(): Flow<PagingData<Waybill>>
    fun createDraftWaybill(): Flow<Resource<Waybill>>

    fun getProducts(
        waybillId: Int
    ): Flow<PagingData<WaybillProduct>>

    suspend fun createWaybillProduct(
        params: CreateWaybillProductUseCase.CreateWaybillProductParams
    ): Either<Throwable, BigDecimal>

    suspend fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.GetWaybillProductByBarcodeParams
    ): Either<Throwable, WaybillProduct>

    suspend fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.GetProductByBarcodeParams
    ): Either<Throwable, ProductEntity>

    suspend fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.UpdateWaybillProductParams
    ): Either<Throwable, BigDecimal>

    suspend fun conductWaybill(
        waybill: Waybill
    ): Either<Throwable, Waybill>

    suspend fun rollbackWaybill(
        waybill: Waybill
    ): Either<Throwable, Waybill>

    suspend fun updateWaybill(
        waybill: WaybillDTO
    ): Either<Throwable, Unit>

}