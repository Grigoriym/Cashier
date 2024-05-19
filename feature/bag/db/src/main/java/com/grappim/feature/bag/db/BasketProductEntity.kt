package com.grappim.feature.bag.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grappim.domain.model.ProductUnit
import java.math.BigDecimal

const val basketEntityTableName = "basket_table"

@Entity(
    tableName = basketEntityTableName
)
data class BasketProductEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val sellingPrice: BigDecimal,
    val amount: BigDecimal,
    val unit: ProductUnit,
    val barcode: String,
    val productId: Long,
    val stockId: String,
    val merchantId: String,
    val basketId: Long
)
