package com.grappim.repository.utils

import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.ProductsDao
import com.grappim.product_category.db.ProductCategoryDao
import javax.inject.Inject

class DataClearHelper @Inject constructor(
    private val productsDao: ProductsDao,
    private val basketDao: BasketDao,
    private val productCategoryDao: ProductCategoryDao
) {

    suspend fun clearDb() {
        productsDao.clearProducts()
        basketDao.clearBasket()
        productCategoryDao.clearCategories()
    }

}