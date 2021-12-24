package com.grappim.db.di

import android.content.Context
import androidx.room.Room
import com.grappim.db.BuildConfig
import com.grappim.db.CashierDatabase
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.CategoryDao
import com.grappim.db.dao.ProductsDao
import com.grappim.di.AppScope
import com.grappim.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @[AppScope Provides]
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): CashierDatabase =
        Room.databaseBuilder(
            context,
            CashierDatabase::class.java,
            "cashier_${BuildConfig.BUILD_TYPE}.db"
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