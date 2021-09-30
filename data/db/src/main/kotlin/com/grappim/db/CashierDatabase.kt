package com.grappim.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grappim.db.converter.AcceptanceStatusConverter
import com.grappim.db.converter.BaseListsConverter
import com.grappim.db.converter.BigDecimalConverter
import com.grappim.db.converter.ProductUnitConverter
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.CategoryDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.BasketProductEntity
import com.grappim.db.entity.CategoryEntity
import com.grappim.db.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        BasketProductEntity::class
    ],
    version = 1,
    exportSchema = true
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