package com.grappim.repository.utils

import com.grappim.feature.bag.db.BasketDao
import com.grappim.db.dao.ProductsDao
import com.grappim.product_category.db.ProductCategoryDao
import javax.inject.Inject

class DataClearHelperImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val basketDao: com.grappim.feature.bag.db.BasketDao,
    private val productCategoryDao: ProductCategoryDao
) : DataClearHelper {

    override suspend fun clearDb() {
        productsDao.clearProducts()
        basketDao.clearBasket()
        productCategoryDao.clearCategories()
    }

}