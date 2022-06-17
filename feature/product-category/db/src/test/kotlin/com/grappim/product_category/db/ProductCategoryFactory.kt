package com.grappim.product_category.db

import com.grappim.product_category.domain.model.ProductCategory

fun createProductCategory(
    id: Long = 1,
    name: String = "name",
    merchantId: String = "merchantId",
    stockId: String = "stockId"
): ProductCategory =
    ProductCategory(
        id = id,
        name = name,
        merchantId = merchantId,
        stockId = stockId
    )

fun createProductCategoryList(
    size: Long = 3,
    name: String = "name",
    merchantId: String = "merchantId",
    stockId: String = "stockId"
): List<ProductCategory> {
    val result = mutableListOf<ProductCategory>()
    (0..size).forEach {
        result.add(
            createProductCategory(
                id = it,
                name = "$name $it",
                merchantId = "$merchantId $it",
                stockId = "$stockId $it"
            )
        )
    }
    return result
}