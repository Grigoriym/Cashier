package com.grappim.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grappim.calculations.bigDecimalZero
import com.grappim.domain.model.ProductUnit
import java.math.BigDecimal

const val PRODUCT_ENTITY_TABLE = "product_table"

@Entity(tableName = PRODUCT_ENTITY_TABLE)
data class ProductEntity(
    @PrimaryKey
    val id: Long,
    val barcode: String,
    val name: String,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val amount: BigDecimal,
    val stockId: String,
    val unit: ProductUnit,
    val merchantId: String,

    val createdOn: String,
    val updatedOn: String,

    @ColumnInfo(defaultValue = "0")
    val categoryId: Long,

    var basketCount: BigDecimal = bigDecimalZero()
)
