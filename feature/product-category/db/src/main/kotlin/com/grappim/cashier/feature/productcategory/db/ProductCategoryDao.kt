package com.grappim.cashier.feature.productcategory.db

import androidx.room.Dao
import androidx.room.Query
import com.grappim.cashier.comon.db.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductCategoryDao : BaseDao<ProductCategoryEntity> {

    @Query("SELECT * FROM $PRODUCT_CATEGORY_TABLE")
    suspend fun getAllCategories(): List<ProductCategoryEntity>

    @Query("SELECT * FROM $PRODUCT_CATEGORY_TABLE")
    fun getAllCategoriesFlow(): Flow<List<ProductCategoryEntity>>

    @Query("DELETE FROM $PRODUCT_CATEGORY_TABLE")
    suspend fun clearCategories()
}
