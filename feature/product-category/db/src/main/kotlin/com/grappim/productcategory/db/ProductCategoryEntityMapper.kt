package com.grappim.productcategory.db

import com.grappim.productcategory.domain.model.ProductCategory
import javax.inject.Inject

class ProductCategoryEntityMapper @Inject constructor(

) {

    fun map(from: ProductCategory): ProductCategoryEntity =
        ProductCategoryEntity(
            id = from.id,
            name = from.name,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    fun mapList(from: List<ProductCategory>): List<ProductCategoryEntity> =
        from.map {
            map(it)
        }

    fun revert(from: ProductCategoryEntity): ProductCategory =
        ProductCategory(
            id = from.id,
            name = from.name,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    fun revertList(from: List<ProductCategoryEntity>): List<ProductCategory> =
        from.map {
            revert(it)
        }
}
