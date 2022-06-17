package com.grappim.products.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.domain.model.Product
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.bag.db.BasketDao
import com.grappim.feature.bag.network.api.BasketApi
import com.grappim.feature.bag.network.mapper.toDomain
import com.grappim.feature.bag.network.mapper.toEntity
import com.grappim.feature.bag.network.model.BasketProductDTO
import com.grappim.logger.logE
import com.grappim.products.network.api.ProductsApi
import com.grappim.products.network.mapper.toDomain
import com.grappim.products.network.model.FilterProductsRequestDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FilterProductsPagingSource constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val productsApi: ProductsApi,
    private val generalStorage: GeneralStorage,
    private val query: String,
    private val basketApi: BasketApi,
    private val basketDao: BasketDao
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = null

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Product> =
        withContext(ioDispatcher) {
            return@withContext try {
                val basketProducts: List<BasketProductDTO> = try {
                    basketApi.getBasketProducts(generalStorage.stockId)
                } catch (e: Exception) {
                    logE(e)
                    emptyList()
                }
                basketDao.clearBasket()
                basketDao.insert(basketProducts.toEntity())

                val nextOffset = params.key ?: 0
                val request = FilterProductsRequestDTO(
                    offset = nextOffset.toLong(),
                    limit = params.loadSize.toLong(),
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.stockId,
                    query = query
                )
                val products = productsApi
                    .filterProducts(request)
                    .products
                    .toDomain()
                val newNextOffset: Int? = if (products.isEmpty()) {
                    null
                } else {
                    nextOffset + params.loadSize
                }
                if (basketProducts.isNotEmpty()) {
                    products.forEach { product ->
                        product.basketProduct = basketProducts.find {
                            it.productId == product.id
                        }?.toDomain()
                    }
                }
                LoadResult.Page(
                    data = products,
                    prevKey = null,
                    nextKey = newNextOffset
                )
            } catch (e: Throwable) {
                logE(e)
                LoadResult.Error(e)
            }
        }

}