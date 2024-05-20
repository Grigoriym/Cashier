package com.grappim.cashier.feature.productcategory.repository

import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.cashier.feature.productcategory.network.model.ProductCategoryDTO
import javax.inject.Inject

class ProductCategoryDTOMapper @Inject constructor() {

    fun map(from: ProductCategoryDTO): ProductCategory = ProductCategory(
        id = from.id,
        name = from.name,
        merchantId = from.merchantId,
        stockId = from.stockId
    )

    fun mapList(from: List<ProductCategoryDTO>): List<ProductCategory> = from.map {
        map(it)
    }
}
