package com.grappim.cashier.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grappim.cashier.data.db.converter.AcceptanceStatusConverter
import com.grappim.cashier.data.db.converter.BaseListsConverter
import com.grappim.cashier.data.db.converter.BigDecimalConverter
import com.grappim.cashier.data.db.converter.ProductUnitConverter
import com.grappim.cashier.data.db.dao.BasketDao
import com.grappim.cashier.data.db.dao.CategoryDao
import com.grappim.cashier.data.db.dao.ProductsDao
import com.grappim.cashier.data.db.entity.BasketProductEntity
import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        BasketProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BigDecimalConverter::class,
    BaseListsConverter::class,
    AcceptanceStatusConverter::class,
    ProductUnitConverter::class
)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    abstract fun basketDao(): BasketDao
    abstract fun categoryDao(): CategoryDao
}