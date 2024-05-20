package com.grappim.cashier.feature.productcategory.presentation.createedit.model

data class CreateEditCategoryData(
    val categoryName: String
) {
    companion object {
        fun empty(): CreateEditCategoryData = CreateEditCategoryData(
            categoryName = ""
        )
    }
}
