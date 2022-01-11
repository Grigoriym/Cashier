package com.grappim.product_category.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val productCategoryEntityTableName = "product_category_table"

@Entity(
    tableName = productCategoryEntityTableName
)
data class ProductCategoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val merchantId: String,
    val stockId: String,
)
