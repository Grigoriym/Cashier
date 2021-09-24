package com.grappim.cashier.di.modules

import android.content.Context
import androidx.room.Room
import com.grappim.cashier.BuildConfig
import com.grappim.cashier.data.db.CashierDatabase
import com.grappim.cashier.data.db.dao.BasketDao
import com.grappim.cashier.data.db.dao.CategoryDao
import com.grappim.cashier.data.db.dao.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
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
    @Singleton
    fun provideProductsDao(
        cashierDatabase: CashierDatabase
    ): ProductsDao = cashierDatabase.productsDao()

    @Provides
    @Singleton
    fun providerBasketDao(
        cashierDatabase: CashierDatabase
    ): BasketDao = cashierDatabase.basketDao()

    @Provides
    @Singleton
    fun provideCategoryDao(
        cashierDatabase: CashierDatabase
    ): CategoryDao = cashierDatabase.categoryDao()

}