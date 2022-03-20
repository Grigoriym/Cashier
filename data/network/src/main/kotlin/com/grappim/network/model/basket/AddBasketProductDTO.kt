package com.grappim.network.model.basket

import com.grappim.common.network.serializers.BigDecimalSerializer
import com.grappim.domain.model.base.ProductUnit
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class AddBasketProductDTO(
    val barcode: String,
    val name: String,
    val productId: Long,
    val stockId: String,
    val merchantId: String,
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,
    val unit: ProductUnit,
    @Serializable(BigDecimalSerializer::class)
    val sellingPrice: BigDecimal
)
