package com.grappim.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.grappim.comon.db.BaseDao
import com.grappim.db.entity.ProductEntity
import com.grappim.db.entity.productEntityTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao : BaseDao<ProductEntity> {

    @Query("DELETE FROM $productEntityTableName")
    suspend fun clearProducts()

    @Query("SELECT * FROM $productEntityTableName")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM $productEntityTableName WHERE name LIKE :query")
    suspend fun searchProducts(query: String): List<ProductEntity>

    @Query("SELECT * FROM $productEntityTableName WHERE categoryId=:categoryId")
    suspend fun searchProductsByCategoryId(categoryId: Long): List<ProductEntity>

    @Query("SELECT * FROM $productEntityTableName WHERE barcode=:barcode")
    suspend fun getProductByBarcode(barcode: String): ProductEntity?

    @RawQuery(observedEntities = [ProductEntity::class])
    fun getProductsFlow(query: SimpleSQLiteQuery): Flow<List<ProductEntity>>

}
