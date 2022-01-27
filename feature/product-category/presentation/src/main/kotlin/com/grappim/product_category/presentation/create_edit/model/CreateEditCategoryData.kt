package com.grappim.product_category.presentation.create_edit.model

data class CreateEditCategoryData(
    val categoryName: String
) {
    companion object {
        fun empty(): CreateEditCategoryData =
            CreateEditCategoryData(
                categoryName = ""
            )
    }
}
