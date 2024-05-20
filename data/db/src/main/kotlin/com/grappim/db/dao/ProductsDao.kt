package com.grappim.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.grappim.cashier.comon.db.BaseDao
import com.grappim.db.entity.PRODUCT_ENTITY_TABLE
import com.grappim.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao : BaseDao<ProductEntity> {

    @Query("DELETE FROM $PRODUCT_ENTITY_TABLE")
    suspend fun clearProducts()

    @Query("SELECT * FROM $PRODUCT_ENTITY_TABLE")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM $PRODUCT_ENTITY_TABLE WHERE name LIKE :query")
    suspend fun searchProducts(query: String): List<ProductEntity>

    @Query("SELECT * FROM $PRODUCT_ENTITY_TABLE WHERE categoryId=:categoryId")
    suspend fun searchProductsByCategoryId(categoryId: Long): List<ProductEntity>

    @Query("SELECT * FROM $PRODUCT_ENTITY_TABLE WHERE barcode=:barcode")
    suspend fun getProductByBarcode(barcode: String): ProductEntity?

    @RawQuery(observedEntities = [ProductEntity::class])
    fun getProductsFlow(query: SimpleSQLiteQuery): Flow<List<ProductEntity>>
}
