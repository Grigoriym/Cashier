package com.grappim.cashier.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.grappim.cashier.api.WaybillApi
import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.map
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.db.dao.ProductsDao
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.data.paging.GetWaybillPagingSource
import com.grappim.cashier.data.paging.GetWaybillProductsPagingSource
import com.grappim.cashier.data.remote.BaseRepository
import com.grappim.cashier.data.remote.model.waybill.*
import com.grappim.cashier.data.remote.model.waybill.WaybillMapper.toDTO
import com.grappim.cashier.data.remote.model.waybill.WaybillMapper.toDomain
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.di.modules.QualifierWaybillApi
import com.grappim.cashier.domain.products.GetProductByBarcodeUseCase
import com.grappim.cashier.domain.repository.WaybillRepository
import com.grappim.cashier.domain.waybill.*
import com.grappim.cashier.ui.waybill.WaybillStatus
import com.grappim.cashier.ui.waybill.WaybillType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaybillRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @QualifierWaybillApi private val waybillApi: WaybillApi,
    private val generalStorage: GeneralStorage,
    private val productsDao: ProductsDao
) : BaseRepository(), WaybillRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    override suspend fun createWaybillProduct(
        params: CreateWaybillProductUseCase.CreateWaybillProductParams
    ): Either<Throwable, BigDecimal> =
        apiCall {
            waybillApi.createWaybillProduct(
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
        }.map {
            it.totalCost
        }

    override suspend fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.GetWaybillProductByBarcodeParams
    ): Either<Throwable, WaybillProduct> = apiCall {
        waybillApi.getWaybillProductByBarcode(
            GetWaybillByBarcodeRequestDTO(
                barcode = params.barcode,
                waybillId = params.waybillId
            )
        )
    }.map {
        if (it.found) {
            withContext(ioDispatcher) {
                it.product.toDomain()
            }
        } else {
            return Either.Left(IllegalArgumentException("not found"))
        }
    }

    override suspend fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.GetProductByBarcodeParams
    ): Either<Throwable, ProductEntity> =
        withContext(ioDispatcher) {
            val product = productsDao.getProductByBarcode(barcode = params.barcode)
            if (product == null) {
                return@withContext Either.Left(IllegalArgumentException("not found"))
            } else {
                return@withContext Either.Right(product)
            }
        }

    override suspend fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.UpdateWaybillProductParams
    ): Either<Throwable, BigDecimal> =
        apiCall {
            waybillApi.updateWaybillProduct(
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
        }.map {
            it.totalCost
        }

    override suspend fun conductWaybill(
        waybill: Waybill
    ): Either<Throwable, Waybill> =
        apiCall {
            updateWaybill(waybill.toDTO())

            waybillApi.conductWaybill(waybill.id)
        }.map {
            withContext(ioDispatcher) {
                it.waybill.toDomain()
            }
        }

    override suspend fun rollbackWaybill(
        waybill: Waybill
    ): Either<Throwable, Waybill> =
        apiCall {
            updateWaybill(waybill.toDTO())

            waybillApi.rollbackWaybill(waybill.id)
        }.map {
            withContext(ioDispatcher) {
                it.waybill.toDomain()
            }
        }

    override suspend fun updateWaybill(
        waybill: WaybillDTO
    ): Either<Throwable, Unit> = apiCall {
        waybillApi.updateWaybill(
            UpdateWaybillRequestDTO(
                waybill
            )
        )
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
                    waybillsDTO.toDomain()
                }
            }

    override fun getProducts(
        waybillId: Int
    ): Flow<PagingData<WaybillProduct>> =
        Pager(
            config = pagingConfig
        ) {
            GetWaybillProductsPagingSource(
                ioDispatcher = ioDispatcher,
                generalStorage = generalStorage,
                waybillApi = waybillApi,
                waybillId = waybillId
            )
        }.flow
            .map {
                it.map { productsDTO ->
                    productsDTO.toDomain()
                }
            }

    override fun createDraftWaybill(): Flow<Resource<Waybill>> =
        flow {
            emit(Resource.Loading)
            val responseId = waybillApi.createWaybill(
                CreateWaybillRequestDTO(
                    waybill = PartialWaybill(
                        merchantId = generalStorage.getMerchantId(),
                        status = WaybillStatus.DRAFT.value,
                        stockId = generalStorage.getStockId(),
                        type = WaybillType.INWAYBILL.value
                    )
                )
            )

            val result = waybillApi.getWaybillById(responseId.id)
            val mappedResult = result.waybill.toDomain()
            emit(Resource.Success(mappedResult))
        }
}