package com.grappim.productcategory.db

import androidx.room.Dao
import androidx.room.Query
import com.grappim.comon.db.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductCategoryDao : BaseDao<ProductCategoryEntity> {

    @Query("SELECT * FROM $productCategoryEntityTableName")
    suspend fun getAllCategories(): List<ProductCategoryEntity>

    @Query("SELECT * FROM $productCategoryEntityTableName")
    fun getAllCategoriesFlow(): Flow<List<ProductCategoryEntity>>

    @Query("DELETE FROM $productCategoryEntityTableName")
    suspend fun clearCategories()

}
