package com.grappim.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grappim.calculations.bigDecimalZero
import com.grappim.domain.model.ProductUnit
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

const val productEntityTableName = "product_table"

@Entity(tableName = productEntityTableName)
@Parcelize
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
) : Parcelable
