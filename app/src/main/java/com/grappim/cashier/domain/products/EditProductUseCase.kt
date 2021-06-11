package com.grappim.cashier.domain.products

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.utils.ProductUnit
import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import java.math.BigDecimal
import javax.inject.Inject

class EditProductUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(
        name: String,
        barcode: String,
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal,
        amount: BigDecimal,
        unit: ProductUnit,
        categoryEntity: CategoryEntity?,
        productEntity: ProductEntity
    ): Either<Throwable, Unit> {
        val params = UpdateProductParams(
            name = name,
            barcode = barcode,
            sellingPrice = sellingPrice,
            purchasePrice = purchasePrice,
            amount = amount,
            unit = unit,
            productEntity = productEntity,
            categoryEntity = categoryEntity
        )
        return generalRepository.updateProduct(params)
    }

    data class UpdateProductParams(
        val name: String,
        val barcode: String,
        val sellingPrice: BigDecimal,
        val purchasePrice: BigDecimal,
        val amount: BigDecimal,
        val unit: ProductUnit,
        val productEntity: ProductEntity,
        val categoryEntity: CategoryEntity?
    )

}

