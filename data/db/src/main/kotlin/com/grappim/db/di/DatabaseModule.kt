package com.grappim.db.di

import android.content.Context
import androidx.room.Room
import com.grappim.db.BuildConfig
import com.grappim.db.CashierDatabase
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.CategoryDao
import com.grappim.db.dao.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Singleton Provides]
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