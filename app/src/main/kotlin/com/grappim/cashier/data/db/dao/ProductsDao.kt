package com.grappim.cashier.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.data.db.entity.productEntityTableName
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
    suspend fun searchProductsByCategoryId(categoryId: Int): List<ProductEntity>

    @Query("SELECT * FROM $productEntityTableName WHERE barcode=:barcode")
    suspend fun getProductByBarcode(barcode: String): ProductEntity?

    @RawQuery(observedEntities = [ProductEntity::class])
    fun getProductsFlow(query: SimpleSQLiteQuery): Flow<List<ProductEntity>>

}