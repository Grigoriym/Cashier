package com.grappim.cashier.data.remote.model.product

import com.grappim.cashier.core.utils.ProductUnit
import com.grappim.cashier.data.db.entity.ProductEntity

object ProductsMapper {

    fun List<ProductDTO>.toDomain(): List<ProductEntity> {
        val resultList = mutableListOf<ProductEntity>()
        this.forEach {
            resultList.add(it.toDomain())
        }
        return resultList
    }

    fun ProductDTO.toDomain(): ProductEntity =
        ProductEntity(
            id = this.id,
            barcode = this.barcode,
            name = this.name,
            sellingPrice = this.sellingPrice,
            purchasePrice = this.purchasePrice,
            amount = this.amount,
            stockId = this.stockId,
            unit = ProductUnit.getProductUnitByValue(this.unit),
            merchantId = this.merchantId,
            createdOn = this.createdOn,
            updatedOn = this.updatedOn,
            categoryId = this.categoryId,
            categoryName = this.category
        )
}