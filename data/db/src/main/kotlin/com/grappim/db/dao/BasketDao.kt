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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basketProductEntity: BasketProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<BasketProductEntity>)

    @Query("DELETE FROM $basketEntityTableName WHERE id=:id")
    suspend fun removeProductByUid(id: Long)

    @Query("SELECT * FROM $basketEntityTableName")
    fun getAllBasketProducts(): Flow<List<BasketProductEntity>>

    @Query("SELECT * FROM $basketEntityTableName")
    suspend fun getBasketProducts(): List<BasketProductEntity>

    @Query("DELETE FROM $basketEntityTableName")
    suspend fun deleteBagProducts()

}