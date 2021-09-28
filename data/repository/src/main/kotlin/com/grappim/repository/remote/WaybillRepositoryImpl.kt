package com.grappim.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.grappim.db.dao.ProductsDao
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
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
import com.grappim.network.di.QualifierWaybillApi
import com.grappim.network.mappers.products.ProductMapper
import com.grappim.network.mappers.waybill.WaybillMapper
import com.grappim.network.mappers.waybill.WaybillProductMapper
import com.grappim.network.model.waybill.*
import com.grappim.repository.paging.GetWaybillPagingSource
import com.grappim.repository.paging.GetWaybillProductsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaybillRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @QualifierWaybillApi private val waybillApi: WaybillApi,
    private val generalStorage: GeneralStorage,
    private val productsDao: ProductsDao,
    private val waybillMapper: WaybillMapper,
    private val waybillProductMapper: WaybillProductMapper,
    private val productMapper: ProductMapper
) : WaybillRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    override fun createWaybillProduct(
        params: CreateWaybillProductUseCase.Params
    ): Flow<Result<BigDecimal>> =
        flow {
            emit(Result.Loading)
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
            emit(Result.Success(totalCost))
        }

    override fun getWaybillProductByBarcode(
        params: GetWaybillProductByBarcodeUseCase.Params
    ): Flow<Result<WaybillProduct>> = flow {
        emit(Result.Loading)
        val response = waybillApi.getWaybillProductByBarcode(
            GetWaybillByBarcodeRequestDTO(
                barcode = params.barcode,
                waybillId = params.waybillId
            )
        )
        if (response.found) {
            val domainToReturn = waybillProductMapper.dtoToDomain(response.product)
            emit(Result.Success(domainToReturn))
        } else {
            throw IllegalArgumentException("not found")
        }
    }

    override fun getProductByBarcode(
        params: GetProductByBarcodeUseCase.Params
    ): Flow<Result<Product>> =
        flow {
            emit(Result.Loading)
            val product = productsDao.getProductByBarcode(barcode = params.barcode)
                ?: throw IllegalArgumentException("not found")
            val domainToReturn = productMapper.entityToDomain(product)
            emit(Result.Success(domainToReturn))
        }

    override fun updateWaybillProduct(
        params: UpdateWaybillProductUseCase.Params
    ): Flow<Result<BigDecimal>> =
        flow {
            emit(Result.Loading)
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
            emit(Result.Success(totalCost))
        }

    override fun conductWaybill(
        params: ConductWaybillUseCase.Params
    ): Flow<Result<Waybill>> =
        flow {
            emit(Result.Loading)

            val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
            waybillApi.updateWaybill(
                UpdateWaybillRequestDTO(dtoToUpdate)
            )

            val response = waybillApi.conductWaybill(params.waybill.id)

            val domainToReturn = waybillMapper.dtoToDomain(response.waybill)
            emit(Result.Success(domainToReturn))
        }

    override fun rollbackWaybill(
        params: RollbackWaybillUseCase.Params
    ): Flow<Result<Waybill>> = flow {
        emit(Result.Loading)
        val dtoToUpdate = waybillMapper.domainToDto(params.waybill)
        waybillApi.updateWaybill(
            UpdateWaybillRequestDTO(dtoToUpdate)
        )

        val response = waybillApi.rollbackWaybill(params.waybill.id)

        val domainToReturn = waybillMapper.dtoToDomain(response.waybill)
        emit(Result.Success(domainToReturn))
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
                    waybillProductMapper.dtoToDomain(productsDTO)
                }
            }

    override fun createDraftWaybill(): Flow<Result<Waybill>> =
        flow {
            emit(Result.Loading)
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
            val mappedResult = waybillMapper.dtoToDomain(result.waybill)
            emit(Result.Success(mappedResult))
        }
}