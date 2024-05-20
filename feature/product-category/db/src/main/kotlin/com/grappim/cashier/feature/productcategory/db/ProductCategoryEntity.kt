package com.grappim.cashier.feature.productcategory.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val PRODUCT_CATEGORY_TABLE = "product_category_table"

@Entity(
    tableName = PRODUCT_CATEGORY_TABLE
)
data class ProductCategoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val merchantId: String,
    val stockId: String
)
