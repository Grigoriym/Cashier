package com.grappim.domain.model.product

import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalZero
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.basket.BasketProduct
import java.io.Serializable
import java.math.BigDecimal

data class Product(
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

    val categoryId: Long,

    var basketProduct: BasketProduct? = null
) : Serializable {
    companion object {
        fun empty(): Product = Product(
            id = 1,
            barcode = "123",
            name = "Product name",
            sellingPrice = "100".asBigDecimal(),
            purchasePrice = "120".asBigDecimal(),
            amount = "6".asBigDecimal(),
            stockId = "123",
            unit = ProductUnit.KG,
            merchantId = "234",
            createdOn = "",
            updatedOn = "",
            categoryId = 0L
        )
    }
}