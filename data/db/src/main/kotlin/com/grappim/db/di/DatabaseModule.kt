package com.grappim.db.di

import android.content.Context
import androidx.room.Room
import com.grappim.db.CashierDatabase
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.CategoryDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.di.configs.DatabaseBuildConfigProvider
import com.grappim.db.di.configs.DatabaseConfigsModule
import com.grappim.di.ApplicationContext
import com.grappim.di.DatabaseScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DatabaseConfigsModule::class
    ]
)
class DatabaseModule {

    @[DatabaseScope Provides]
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        databaseBuildConfigProvider: DatabaseBuildConfigProvider
    ): CashierDatabase =
        Room.databaseBuilder(
            context,
            CashierDatabase::class.java,
            "cashier_${databaseBuildConfigProvider.buildType}.db"
        )
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()

    @Provides
    fun provideProductsDao(
        cashierDatabase: CashierDatabase
    ): ProductsDao = cashierDatabase.productsDao()

    @Provides
    fun providerBasketDao(
        cashierDatabase: CashierDatabase
    ): BasketDao = cashierDatabase.basketDao()

    @Provides
    fun provideCategoryDao(
        cashierDatabase: CashierDatabase
    ): CategoryDao = cashierDatabase.categoryDao()

}