package com.grappim.productcategory.presentation.createedit.model

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
