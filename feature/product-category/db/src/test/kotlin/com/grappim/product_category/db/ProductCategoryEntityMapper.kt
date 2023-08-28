package com.grappim.product_category.db

import com.grappim.productcategory.db.ProductCategoryEntity

fun createProductCategoryEntity(
    id: Long = 1,
    name: String = "name",
    merchantId: String = "merchantId",
    stockId: String = "stockId"
): ProductCategoryEntity =
    ProductCategoryEntity(
        id = id,
        name = name,
        merchantId = merchantId,
        stockId = stockId
    )

fun createProductCategoryEntityList(
    size: Long = 3,
    name: String = "name",
    merchantId: String = "merchantId",
    stockId: String = "stockId"
): List<ProductCategoryEntity> {
    val result = mutableListOf<ProductCategoryEntity>()
    (0..size).forEach {
        result.add(
            createProductCategoryEntity(
                id = it,
                name = "$name $it",
                merchantId = "$merchantId $it",
                stockId = "$stockId $it"
            )
        )
    }
    return result
}