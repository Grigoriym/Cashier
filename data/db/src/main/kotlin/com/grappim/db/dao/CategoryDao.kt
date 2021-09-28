package com.grappim.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.grappim.db.entity.CategoryEntity
import com.grappim.db.entity.categoryEntityTableName

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM $categoryEntityTableName")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("DELETE FROM $categoryEntityTableName")
    suspend fun clearCategories()
}