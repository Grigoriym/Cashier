package com.grappim.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.grappim.comon.db.BaseDao
import com.grappim.db.entity.CategoryEntity
import com.grappim.db.entity.categoryEntityTableName

@Dao
@Deprecated(
    message = "remove it"
)
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM $categoryEntityTableName")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("DELETE FROM $categoryEntityTableName")
    suspend fun clearCategories()
}