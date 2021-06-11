package com.grappim.cashier.data.remote.model.category

import com.grappim.cashier.data.db.entity.CategoryEntity

object CategoryMapper {

    fun List<CategoryDTO>.toDomain(): List<CategoryEntity> {
        val resultList = mutableListOf<CategoryEntity>()
        this.forEach {
            resultList.add(it.toDomain())
        }
        return resultList
    }

    fun CategoryDTO.toDomain(): CategoryEntity =
        CategoryEntity(
            id = this.id,
            name = this.name,
            merchantId = this.merchantId,
            stockId = this.stockId
        )

}