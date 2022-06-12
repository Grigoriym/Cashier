package com.grappim.domain.repository

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.common.lce.VoidTry
import com.grappim.domain.interactor.products.GetProductBarcodeParams
import com.grappim.domain.interactor.waybill.*
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
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

    suspend fun getProductByBarcode(
        params: GetProductBarcodeParams
    ): Try<Product, Throwable>

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