package com.grappim.network.mappers.category

import com.grappim.db.entity.CategoryEntity
import com.grappim.domain.model.product.Category
import com.grappim.network.model.category.CategoryDTO
import javax.inject.Inject

@Deprecated(
    message = "use product category"
)
class CategoryMapper @Inject constructor(

) {

    suspend fun dtoToEntity(from: CategoryDTO): CategoryEntity =
        CategoryEntity(
            id = from.id,
            name = from.name,
            merchantId = from.merchantId,
            stockId = from.stockId
        )

    suspend fun dtoToEntityList(from: List<CategoryDTO>): List<CategoryEntity> =
        ArrayList<CategoryEntity>(from.size).apply {
            from.forEach {
                add(dtoToEntity(it))
            }
        }

    suspend fun dbToDomain(
        db: CategoryEntity
    ): Category =
        Category(
            id = db.id,
            name = db.name,
            merchantId = db.merchantId,
            stockId = db.stockId,
            isDefault = db.isDefault
        )

    suspend fun dbToDomainList(
        dbList: List<CategoryEntity>
    ): List<Category> = ArrayList<Category>(dbList.size).apply {
        dbList.forEach {
            add(dbToDomain(it))
        }
    }

}