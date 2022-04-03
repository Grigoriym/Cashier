package com.grappim.network.mappers.products

import com.grappim.db.entity.ProductEntity
import com.grappim.domain.model.product.Product
import com.grappim.network.model.products.ProductDTO

fun ProductDTO.toEntity(): ProductEntity =
    ProductEntity(
        id = this.id,
        barcode = this.barcode,
        name = this.name,
        sellingPrice = this.sellingPrice,
        purchasePrice = this.purchasePrice,
        amount = this.amount,
        stockId = this.stockId,
        unit = this.unit,
        merchantId = this.merchantId,
        createdOn = this.createdOn,
        updatedOn = this.updatedOn,
        categoryId = this.categoryId
    )

fun ProductEntity.toDomain(): Product =
    Product(
        id = this.id,
        barcode = this.barcode,
        name = this.name,
        sellingPrice = this.sellingPrice,
        purchasePrice = this.purchasePrice,
        amount = this.amount,
        stockId = this.stockId,
        unit = this.unit,
        merchantId = this.merchantId,
        createdOn = this.createdOn,
        updatedOn = this.updatedOn,
        categoryId = this.categoryId
    )

fun List<ProductEntity>.toDomain2(): List<Product> =
    ArrayList<Product>(this.size).apply {
        this@toDomain2.forEach {
            add(it.toDomain())
        }
    }

fun ProductDTO.toDomain(): Product =
    Product(
        id = this.id,
        barcode = this.barcode,
        name = this.name,
        sellingPrice = this.sellingPrice,
        purchasePrice = this.purchasePrice,
        amount = this.amount,
        stockId = this.stockId,
        unit = this.unit,
        merchantId = this.merchantId,
        createdOn = this.createdOn,
        updatedOn = this.updatedOn,
        categoryId = this.categoryId
    )

fun List<ProductDTO>.toDomain(): List<Product> =
    this.map {
        it.toDomain()
    }

fun Product.toEntity(): ProductEntity =
    ProductEntity(
        id = this.id,
        barcode = this.barcode,
        name = this.name,
        sellingPrice = this.sellingPrice,
        purchasePrice = this.purchasePrice,
        amount = this.amount,
        stockId = this.stockId,
        unit = this.unit,
        merchantId = this.merchantId,
        createdOn = this.createdOn,
        updatedOn = this.updatedOn,
        categoryId = this.categoryId
    )

fun Product.toDTO(): ProductDTO = ProductDTO(
    id = id,
    barcode = barcode,
    name = name,
    stockId = stockId,
    amount = amount,
    unit = unit,
    merchantId = merchantId,
    purchasePrice = purchasePrice,
    sellingPrice = sellingPrice,
    createdOn = createdOn,
    updatedOn = updatedOn,
    categoryId = categoryId
)

fun List<Product>.toEntity(): List<ProductEntity> =
    this.map {
        it.toEntity()
    }