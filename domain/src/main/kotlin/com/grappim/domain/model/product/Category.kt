package com.grappim.domain.model.product

data class Category(
    val id: Int,
    val name: String,
    val merchantId: String,
    val stockId: String,
    val isDefault: Boolean
) {
    companion object {
        fun empty(): Category =
            Category(
                id = 1,
                name = "name",
                merchantId = "123",
                stockId = "234",
                isDefault = false
            )
    }
}