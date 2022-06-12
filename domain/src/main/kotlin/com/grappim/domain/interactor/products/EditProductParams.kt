package com.grappim.domain.interactor.products

import com.grappim.domain.model.base.ProductUnit
import java.math.BigDecimal

data class EditProductParams(
    val name: String,
    val barcode: String,
    val sellingPrice: BigDecimal,
    val purchasePrice: BigDecimal,
    val amount: BigDecimal,
    val unit: ProductUnit,
    val productMerchantId: String,
    val productCreatedOn: String,
    val productId: Long,
    val productStockId: String,
    val categoryId: Long,
    val category: String
)
