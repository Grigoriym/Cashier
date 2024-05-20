package com.grappim.feature.bag.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBasketProduct(basketProductEntity: BasketProductEntity)

    @Query("DELETE FROM $BASKET_ENTITY_TABLE")
    suspend fun clearBasket()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basketProductEntity: BasketProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<BasketProductEntity>)

    @Query("DELETE FROM $BASKET_ENTITY_TABLE WHERE id=:id")
    suspend fun removeProductByUid(id: Long)

    @Query("SELECT * FROM $BASKET_ENTITY_TABLE")
    fun getAllBasketProducts(): Flow<List<BasketProductEntity>>

    @Query("SELECT * FROM $BASKET_ENTITY_TABLE")
    suspend fun getBasketProducts(): List<BasketProductEntity>

    @Query("DELETE FROM $BASKET_ENTITY_TABLE")
    suspend fun deleteBagProducts()
}
