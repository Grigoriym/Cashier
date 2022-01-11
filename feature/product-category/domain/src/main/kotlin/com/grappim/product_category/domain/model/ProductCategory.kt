package com.grappim.product_category.domain.model

data class ProductCategory(
    val id: Long,
    val name: String,
    val merchantId: String,
    val stockId: String
) {
    companion object {
        fun empty(): ProductCategory =
            ProductCategory(
                id = 1L,
                name = "Category 1",
                merchantId = "",
                stockId = ""
            )
    }
}