package com.grappim.network.mappers

import com.grappim.db.entity.BasketProductEntity
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.product.Product
import com.grappim.network.model.basket.AddBasketProductDTO
import com.grappim.network.model.basket.BasketProductDTO

fun BasketProductDTO.toDomain(): BasketProduct =
    BasketProduct(
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

fun BasketProductEntity.toDomain(): BasketProduct =
    BasketProduct(
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

fun BasketProductDTO.toEntity(): BasketProductEntity =
    BasketProductEntity(
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

fun BasketProduct.toEntity(): BasketProductEntity =
    BasketProductEntity(
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

fun List<BasketProduct>.toEntity2(): List<BasketProductEntity> =
    this.map {
        it.toEntity()
    }

fun List<BasketProductDTO>.toEntity(): List<BasketProductEntity> =
    this.map {
        it.toEntity()
    }

fun List<BasketProductDTO>.toDomain(): List<BasketProduct> =
    this.map {
        it.toDomain()
    }

fun List<BasketProductEntity>.toDomain2(): List<BasketProduct> =
    this.map {
        it.toDomain()
    }

fun BasketProduct.toDTO(): BasketProductDTO =
    BasketProductDTO(
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

fun BasketProduct.toAddBasketProductDTO(): AddBasketProductDTO =
    AddBasketProductDTO(
        barcode = barcode,
        name = name,
        productId = productId,
        stockId = stockId,
        merchantId = merchantId,
        amount = amount,
        unit = unit,
        sellingPrice = sellingPrice
    )

fun Product.toAddBasketProductDTO(): AddBasketProductDTO =
    AddBasketProductDTO(
        barcode = barcode,
        name = name,
        productId = id,
        stockId = stockId,
        merchantId = merchantId,
        amount = amount,
        unit = unit,
        sellingPrice = sellingPrice
    )

fun Product.toBasketProductDTO(): BasketProductDTO =
    BasketProductDTO(
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