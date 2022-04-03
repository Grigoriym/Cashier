package com.grappim.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.db.dao.ProductsDao
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.*
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.domain.model.waybill.WaybillType
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.WaybillApi
import com.grappim.network.di.api.QualifierWaybillApi
import com.grappim.network.mappers.products.toDomain
import com.grappim.network.mappers.waybill.WaybillMapper
import com.grappim.network.mappers.waybill.toDomain
import com.grappim.network.model.waybill.*
import com.grappim.repository.paging.GetWaybillPagingSource
import com.grappim.repository.paging.GetWaybillProductsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject

@AppScope
class WaybillRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @QualifierWaybillApi private val waybillApi: WaybillApi,
    private val generalStorage: GeneralStorage,
    private val productsDao: ProductsDao,
    private val waybillMapper: WaybillMapper
) : WaybillRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    override fun createWaybillProduct(
        params: CreateWaybillProductUseCase.Params
    ): Flow<Try<BigDecimal>> =
        flow {
            emit(Try.Loading)
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

            val totalCost = response.totalCost
            emit(Try.Success(totalCost))
        }

    override fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.Params
    ): Flow<Try<WaybillProduct>> = flow {
        emit(Try.Loading)
        val response = waybillApi.getWaybillProductByBarcode(
            GetWaybillByBarcodeRequestDTO(
                barcode = params.barcode,
                waybillId = params.waybillId
            )
        )
        val domainToReturn = response.product.toDomain()
        emit(Try.Success(domainToReturn))
    }

    override fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.Params
    ): Flow<Try<Product>> =
        flow {
            emit(Try.Loading)
            val product = productsDao.getProductByBarcode(barcode = params.barcode)
                ?: error("not found")
            val domainToReturn = product.toDomain()
            emit(Try.Success(domainToReturn))
        }

    override fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.Params
    ): Flow<Try<BigDecimal>> =
        flow {
            emit(Try.Loading)
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
            emit(Try.Success(totalCost))
        }

    override fun conductWaybill(
        params: ConductWaybillUseCase.Params
    ): Flow<Try<Unit>> =
        flow {
            emit(Try.Loading)

            val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
            waybillApi.updateWaybill(
                UpdateWaybillRequestDTO(dtoToUpdate)
            )

            waybillApi.conductWaybill(params.waybill.id)
            emit(Try.Success(Unit))
        }

    override fun rollbackWaybill(
        params: RollbackWaybillUseCase.Params
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
        val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
        waybillApi.updateWaybill(
            UpdateWaybillRequestDTO(dtoToUpdate)
        )
        waybillApi.rollbackWaybill(params.waybill.id)
        emit(Try.Success(Unit))
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
        params: GetWaybillProductsUseCase.Params
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

    override fun createDraftWaybill(): Flow<Try<Waybill>> =
        flow {
            emit(Try.Loading)
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
            emit(Try.Success(mappedResult))
        }
}