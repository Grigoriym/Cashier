package com.grappim.repository.remote

import com.grappim.common.lce.Try
import com.grappim.db.dao.BasketDao
import com.grappim.domain.interactor.sales.AddBasketProductParams
import com.grappim.domain.interactor.sales.AddProductToBasketParams
import com.grappim.domain.interactor.sales.RemoveProductParams
import com.grappim.domain.interactor.sales.SubtractProductFromBasketParams
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logE
import com.grappim.network.api.BasketApi
import com.grappim.network.di.api.QualifierBasketApi
import com.grappim.network.mappers.*
import com.grappim.common.asynchronous.doOnError
import com.grappim.common.asynchronous.mapSuccess
import com.grappim.common.asynchronous.runOperationCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    @QualifierBasketApi private val basketApi: BasketApi,
    private val generalStorage: GeneralStorage,
    private val basketDao: BasketDao
) : BasketRepository {

    override fun getBasketItems(): Flow<List<BasketProduct>> =
        basketDao.getAllBasketProducts().map {
            it.toDomain2()
        }

    override suspend fun addBasketProduct(
        params: AddBasketProductParams
    ): Try<BasketProduct, Throwable> = runOperationCatching {
        val addBasketProduct = params.product.toAddBasketProductDTO()
        basketApi.addProductToBasket(addBasketProduct)
    }.mapSuccess { dto ->
        dto.toDomain()
    }.doOnError { e ->
        logE(e)
    }

    override suspend fun addProductToBasket(
        params: AddProductToBasketParams
    ): Try<BasketProduct, Throwable> = runOperationCatching {
        val addBasketProduct = params.product.toAddBasketProductDTO()
        val dto = basketApi.addProductToBasket(addBasketProduct)
        dto.toDomain()
    }

    override suspend fun getBasketProducts(): Try<List<BasketProduct>, Throwable> =
        runOperationCatching {
            val products = basketApi.getBasketProducts(
                stockId = generalStorage.stockId
            ).map {
                it.toDomain()
            }
            products
        }

    override suspend fun subtractProduct(
        params: RemoveProductParams
    ): Try<BasketProduct?, Throwable> = runOperationCatching {
        val response = basketApi.subtractProduct(
            params.product.toDTO()
        )
        response.basketProduct?.toDomain()
    }

    override suspend fun subtractProduct(
        params: SubtractProductFromBasketParams
    ): Try<BasketProduct, Throwable> = runOperationCatching {
        val response = basketApi.subtractProduct(
            params.product.toBasketProductDTO()
        )
        response.basketProduct?.toDomain()!!
    }

    override suspend fun clearBasket(): Try<Unit, Throwable> = runOperationCatching {
        basketApi.clearBasket()
    }.doOnError { e ->
        logE(e)
    }
}