package com.grappim.productcategory.repository

import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.network.model.ProductCategoryDTO
import javax.inject.Inject

class ProductCategoryDTOMapper @Inject constructor(

) {

    fun map(
        from: ProductCategoryDTO
    ): ProductCategory =
        ProductCategory(
            id = from.id,
            name = from.name,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    fun mapList(
        from: List<ProductCategoryDTO>
    ): List<ProductCategory> =
        from.map {
            map(it)
        }
}
