package com.grappim.db.di

import android.content.Context
import androidx.room.Room
import com.grappim.cashier.common.di.ApplicationContext
import com.grappim.cashier.common.di.DatabaseScope
import com.grappim.cashier.feature.productcategory.db.ProductCategoryDao
import com.grappim.db.CashierDatabase
import com.grappim.db.dao.ProductsDao
import com.grappim.db.di.configs.DatabaseBuildConfigProvider
import com.grappim.db.di.configs.DatabaseConfigsModule
import com.grappim.feature.bag.db.BasketDao
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DatabaseConfigsModule::class
    ]
)
object DatabaseModule {

    @[DatabaseScope Provides]
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        databaseBuildConfigProvider: DatabaseBuildConfigProvider
    ): CashierDatabase = Room.databaseBuilder(
        context,
        CashierDatabase::class.java,
        "cashier_${databaseBuildConfigProvider.buildType}.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideProductsDao(cashierDatabase: CashierDatabase): ProductsDao =
        cashierDatabase.productsDao()

    @Provides
    fun providerBasketDao(cashierDatabase: CashierDatabase): BasketDao = cashierDatabase.basketDao()

    @Provides
    fun provideProductCategoryDao(cashierDatabase: CashierDatabase): ProductCategoryDao =
        cashierDatabase.productCategoryDao()
}
