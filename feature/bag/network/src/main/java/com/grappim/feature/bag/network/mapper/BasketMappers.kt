package com.grappim.feature.bag.network.mapper

import com.grappim.domain.model.BasketProduct
import com.grappim.domain.model.Product
import com.grappim.feature.bag.db.BasketProductEntity
import com.grappim.feature.bag.network.model.AddBasketProductDTO
import com.grappim.feature.bag.network.model.BasketProductDTO

fun BasketProductDTO.toDomain(): BasketProduct = BasketProduct(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
)

fun BasketProductEntity.toDomain(): BasketProduct = BasketProduct(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
)

fun BasketProductDTO.toEntity(): BasketProductEntity = BasketProductEntity(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
)

fun BasketProduct.toEntity(): BasketProductEntity = BasketProductEntity(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
)

fun List<BasketProduct>.toEntity2(): List<BasketProductEntity> = this.map {
    it.toEntity()
}

fun List<BasketProductDTO>.toEntity(): List<BasketProductEntity> = this.map {
    it.toEntity()
}

fun List<BasketProductDTO>.toDomain(): List<BasketProduct> = this.map {
    it.toDomain()
}

fun List<BasketProductEntity>.toDomain2(): List<BasketProduct> = this.map {
    it.toDomain()
}

fun BasketProduct.toDTO(): BasketProductDTO = BasketProductDTO(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
)

fun BasketProduct.toAddBasketProductDTO(): AddBasketProductDTO = AddBasketProductDTO(
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice
)

fun Product.toAddBasketProductDTO(): AddBasketProductDTO = AddBasketProductDTO(
    barcode = barcode,
    name = name,
    productId = id,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice
)

fun Product.toBasketProductDTO(): BasketProductDTO = BasketProductDTO(
    id = basketProduct?.id!!,
    barcode = barcode,
    name = name,
    productId = id,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketProduct?.basketId!!
)
