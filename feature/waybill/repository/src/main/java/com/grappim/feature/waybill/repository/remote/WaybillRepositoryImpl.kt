package com.grappim.feature.waybill.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.asynchronous.runOperationCatching
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.common.lce.VoidTry
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.waybill.domain.interactor.conductWaybill.ConductWaybillParams
import com.grappim.feature.waybill.domain.interactor.createWaybillProduct.CreateWaybillProductParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProducts.GetWaybillProductsParams
import com.grappim.feature.waybill.domain.interactor.rollbackWaybill.RollbackWaybillParams
import com.grappim.feature.waybill.domain.interactor.updateWaybillProduct.UpdateWaybillProductParams
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.model.WaybillStatus
import com.grappim.feature.waybill.domain.model.WaybillType
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.feature.waybill.network.paging.GetWaybillPagingSource
import com.grappim.feature.waybill.network.paging.GetWaybillProductsPagingSource
import com.grappim.feature.waybill.network.di.QualifierWaybillApi
import com.grappim.feature.waybill.network.api.WaybillApi
import com.grappim.feature.waybill.network.mapper.WaybillMapper
import com.grappim.feature.waybill.network.mapper.toDomain
import com.grappim.feature.waybill.network.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject

@AppScope
class WaybillRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @QualifierWaybillApi private val waybillApi: WaybillApi,
    private val generalStorage: GeneralStorage,
    private val waybillMapper: WaybillMapper
) : WaybillRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    override suspend fun createWaybillProduct(
        params: CreateWaybillProductParams
    ): Try<BigDecimal, Throwable> =
        runOperationCatching {
            val response = waybillApi.createWaybillProduct(
                CreateWaybillProductRequestDTO(
                    product = PartialWaybillProductDTO(
                        amount = params.amount,
                        barcode = params.barcode,
                        name = params.name,
                        purchasePrice = params.purchasePrice,
                        sellingPrice = params.sellingPrice,
                        waybillId = params.waybillId,
                        productId = params.productId
                    )
                )
            )
            response.totalCost
        }

    override suspend fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeParams
    ): Try<WaybillProduct, Throwable> = runOperationCatching {
        val response = waybillApi.getWaybillProductByBarcode(
            GetWaybillByBarcodeRequestDTO(
                barcode = params.barcode,
                waybillId = params.waybillId
            )
        )
        val domainToReturn = response.product.toDomain()
        domainToReturn
    }

    override suspend fun updateWaybillProduct(
        params: UpdateWaybillProductParams
    ): Try<BigDecimal, Throwable> = runOperationCatching {
        val response = waybillApi.updateWaybillProduct(
            CreateWaybillProductRequestDTO(
                product = PartialWaybillProductDTO(
                    amount = params.amount,
                    barcode = params.barcode,
                    name = params.name,
                    purchasePrice = params.purchasePrice,
                    sellingPrice = params.sellingPrice,
                    waybillId = params.waybillId,
                    productId = params.productId,
                    id = params.id
                )
            )
        )
        val totalCost = response.totalCost
        totalCost
    }

    override suspend fun conductWaybill(
        params: ConductWaybillParams
    ): Try<Unit, Throwable> =
        runOperationCatching {
            val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
            waybillApi.updateWaybill(
                UpdateWaybillRequestDTO(dtoToUpdate)
            )
            waybillApi.conductWaybill(params.waybill.id)
        }

    override suspend fun rollbackWaybill(
        params: RollbackWaybillParams
    ): VoidTry<Throwable> = runOperationCatching {
        val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
        waybillApi.updateWaybill(
            UpdateWaybillRequestDTO(dtoToUpdate)
        )
        waybillApi.rollbackWaybill(params.waybill.id)
    }

    override fun getAcceptanceListPaging(): Flow<PagingData<Waybill>> =
        Pager(
            config = pagingConfig
        ) {
            GetWaybillPagingSource(
                ioDispatcher = ioDispatcher,
                generalStorage = generalStorage,
                waybillApi = waybillApi
            )
        }.flow
            .map {
                it.map { waybillsDTO ->
                    waybillMapper.dtoToDomain(waybillsDTO)
                }
            }

    override fun getProducts(
        params: GetWaybillProductsParams
    ): Flow<PagingData<WaybillProduct>> =
        Pager(
            config = pagingConfig
        ) {
            GetWaybillProductsPagingSource(
                ioDispatcher = ioDispatcher,
                waybillApi = waybillApi,
                waybillId = params.waybillId
            )
        }.flow
            .map {
                it.map { productsDTO ->
                    productsDTO.toDomain()
                }
            }

    override suspend fun createDraftWaybill(): Try<Waybill, Throwable> =
        runOperationCatching {
            val responseId = waybillApi.createWaybill(
                CreateWaybillRequestDTO(
                    waybill = PartialWaybill(
                        merchantId = generalStorage.getMerchantId(),
                        status = WaybillStatus.DRAFT,
                        stockId = generalStorage.stockId,
                        type = WaybillType.INWAYBILL.value
                    )
                )
            )
            val mappedResult = waybillMapper.dtoToDomain(responseId.waybill)
            mappedResult
        }
}