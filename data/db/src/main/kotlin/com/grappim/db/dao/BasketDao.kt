package com.grappim.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.grappim.db.entity.BasketProductEntity
import com.grappim.db.entity.basketEntityTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBasketProduct(basketProductEntity: BasketProductEntity)

    @Query("DELETE FROM $basketEntityTableName")
    suspend fun clearBasket()

    @Query("SELECT * FROM $basketEntityTableName WHERE id=:id LIMIT 1")
    suspend fun getProductById(id: Long): BasketProductEntity

    @Query("SELECT * FROM $basketEntityTableName WHERE id IN (:ids)")
    suspend fun getProductsByUids(ids: List<Long>): List<BasketProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basketProductEntity: BasketProductEntity): Long

    @Query("UPDATE $basketEntityTableName SET basketCount = basketCount + 1 WHERE id=:id")
    suspend fun incBasketProduct(id: Long)

    @Transaction
    suspend fun insertOrUpdate(basketProductEntity: BasketProductEntity) {
        val id = insert(basketProductEntity)
        if (id == -1L) incBasketProduct(basketProductEntity.id)
    }

    @Query("DELETE FROM $basketEntityTableName WHERE id=:id")
    suspend fun removeProductByUid(id: Long)

    @Query("SELECT * FROM $basketEntityTableName")
    fun getAllBasketProducts(): Flow<List<BasketProductEntity>>

    @Query("SELECT * FROM $basketEntityTableName")
    suspend fun getBasketProducts(): List<BasketProductEntity>

    @Query("DELETE FROM $basketEntityTableName")
    suspend fun deleteBagProducts()

}