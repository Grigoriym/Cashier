package com.grappim.repository.remote

import com.grappim.cashier.common.async.di.ApplicationScope
import com.grappim.cashier.common.async.di.IoDispatcher
import com.grappim.cashier.common.di.AppScope
import com.grappim.db.dao.ProductsDao
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.bag.db.BasketDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class GeneralRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val productsDao: ProductsDao,
    private val generalStorage: GeneralStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val applicationScope: CoroutineScope
) : GeneralRepository {

    override suspend fun clearData() = withContext(ioDispatcher) {
        applicationScope.launch {
            generalStorage.clearData()
            basketDao.clearBasket()
            productsDao.clearProducts()
        }.join()
    }
}
