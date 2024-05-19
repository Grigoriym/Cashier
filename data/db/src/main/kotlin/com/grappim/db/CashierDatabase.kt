package com.grappim.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grappim.db.converter.BaseListsConverter
import com.grappim.db.converter.BigDecimalConverter
import com.grappim.db.converter.ProductUnitConverter
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.ProductEntity
import com.grappim.feature.bag.db.BasketDao
import com.grappim.feature.bag.db.BasketProductEntity
import com.grappim.productcategory.db.ProductCategoryDao
import com.grappim.productcategory.db.ProductCategoryEntity

@Database(
    entities = [
        ProductEntity::class,
        BasketProductEntity::class,
        ProductCategoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    BigDecimalConverter::class,
    BaseListsConverter::class,
    ProductUnitConverter::class
)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    abstract fun basketDao(): BasketDao
    abstract fun productCategoryDao(): ProductCategoryDao
}
