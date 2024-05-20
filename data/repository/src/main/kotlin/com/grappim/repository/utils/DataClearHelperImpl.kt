package com.grappim.repository.utils

import com.grappim.cashier.data.repositoryapi.DataClearHelper
import com.grappim.cashier.feature.productcategory.db.ProductCategoryDao
import com.grappim.db.dao.ProductsDao
import com.grappim.feature.bag.db.BasketDao
import javax.inject.Inject

class DataClearHelperImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val basketDao: BasketDao,
    private val productCategoryDao: ProductCategoryDao
) : DataClearHelper {

    override suspend fun clearDb() {
        productsDao.clearProducts()
        basketDao.clearBasket()
        productCategoryDao.clearCategories()
    }
}
