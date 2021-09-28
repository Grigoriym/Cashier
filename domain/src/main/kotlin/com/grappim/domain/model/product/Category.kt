package com.grappim.domain.model.product

data class Category(
    val id: Int,
    val name: String,
    val merchantId: String,
    val stockId: String,
    val isDefault: Boolean
)