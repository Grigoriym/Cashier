package com.grappim.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

const val basketEntityTableName = "basket_table"

@Entity(
    tableName = basketEntityTableName
)
@Parcelize
data class BasketProductEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    var basketCount: BigDecimal,
    val amount: BigDecimal,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val barcode: String
) : Parcelable