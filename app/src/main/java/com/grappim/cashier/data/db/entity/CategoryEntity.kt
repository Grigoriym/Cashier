package com.grappim.cashier.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

const val categoryEntityTableName = "category_table"

@Entity(
    tableName = categoryEntityTableName
)
@Parcelize
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val merchantId: String,
    val stockId: String,
    val isDefault: Boolean = false
) : Parcelable
