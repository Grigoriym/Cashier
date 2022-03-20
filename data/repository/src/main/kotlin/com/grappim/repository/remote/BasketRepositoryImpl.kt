package com.grappim.repository.remote

import com.grappim.common.lce.Try
import com.grappim.db.dao.BasketDao
import com.grappim.domain.interactor.sales.AddBasketProduct
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SubtractProductFromBasket
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.BasketApi
import com.grappim.network.di.api.QualifierBasketApi
import com.grappim.network.mappers.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun addBasketProduct(
        params: AddBasketProduct.Params
    ): Flow<Try<BasketProduct>> = flow {
        emit(Try.Loading)
        val addBasketProduct = params.product.toAddBasketProductDTO()
        val dto = basketApi.addProductToBasket(addBasketProduct)
        emit(Try.Success(dto.toDomain()))
    }

    override fun addProductToBasket(
        params: AddProductToBasketUseCase.Params
    ): Flow<Try<BasketProduct>> = flow {
        emit(Try.Loading)
        val addBasketProduct = params.product.toAddBasketProductDTO()
        val dto = basketApi.addProductToBasket(addBasketProduct)
        emit(Try.Success(dto.toDomain()))
    }

    override fun getBasketProducts(): Flow<Try<List<BasketProduct>>> =
        flow {
            emit(Try.Loading)
            val products = basketApi.getBasketProducts(
                stockId = generalStorage.stockId
            ).map {
                it.toDomain()
            }
            emit(Try.Success(products))
        }

    override fun subtractProduct(
        params: RemoveProductUseCase.Params
    ): Flow<Try<BasketProduct?>> = flow {
        emit(Try.Loading)
        val response = basketApi.subtractProduct(
            params.product.toDTO()
        )
        emit(Try.Success(response.basketProduct?.toDomain()))
    }

    override fun subtractProduct(
        params: SubtractProductFromBasket.Params
    ): Flow<Try<BasketProduct>> = flow {
        emit(Try.Loading)
        val response = basketApi.subtractProduct(
            params.product.toBasketProductDTO()
        )
        emit(Try.Success(response.basketProduct?.toDomain()!!))
    }

    override fun clearBasket(): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
        basketApi.clearBasket()
        emit(Try.Success(Unit))
    }
}