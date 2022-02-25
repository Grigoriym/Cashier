package com.grappim.product_category.domain.model

import java.io.Serializable

data class ProductCategory(
    val id: Long,
    val name: String,
    val merchantId: String,
    val stockId: String
) : Serializable {

    fun isCreateCategory(): Boolean = this.id == CREATE_CATEGORY_ID

    companion object {

        const val ALL_DEFAULT_ID = -1L
        const val CREATE_CATEGORY_ID = -2L

        fun empty(): ProductCategory =
            ProductCategory(
                id = 1L,
                name = "Category 1",
                merchantId = "",
                stockId = ""
            )

        fun allItem(): ProductCategory =
            ProductCategory(
                id = ALL_DEFAULT_ID,
                name = "All",
                merchantId = "",
                stockId = ""
            )

        fun createCategory(): ProductCategory =
            ProductCategory(
                id = CREATE_CATEGORY_ID,
                name = "Create category",
                merchantId = "",
                stockId = ""
            )
    }
}