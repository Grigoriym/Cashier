package com.grappim.network.mappers.products

import com.grappim.db.entity.BasketProductEntity
import com.grappim.db.entity.ProductEntity
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.product.Product
import com.grappim.network.model.products.ProductDTO
import javax.inject.Inject

class ProductMapper @Inject constructor(

) {

    fun domainToBasketEntity(
        from: Product
    ): BasketProductEntity =
        BasketProductEntity(
            id = from.id,
            name = from.name,
            basketCount = from.basketCount,
            sellingPrice = from.sellingPrice,
            amount = from.amount,
            purchasePrice = from.purchasePrice,
            barcode = from.barcode
        )

    fun entityToBasketDomain(
        from: BasketProductEntity
    ): BasketProduct =
        BasketProduct(
            id = from.id,
            name = from.name,
            basketCount = from.basketCount,
            amount = from.amount,
            sellingPrice = from.sellingPrice,
            purchasePrice = from.purchasePrice,
            barcode = from.barcode
        )

    fun entityToBasketDomainList(
        from: List<BasketProductEntity>
    ): List<BasketProduct> =
        ArrayList<BasketProduct>(from.size).apply {
            from.forEach {
                add(entityToBasketDomain(it))
            }
        }

    fun dtoToEntity(from: ProductDTO): ProductEntity =
        ProductEntity(
            id = from.id,
            barcode = from.barcode,
            name = from.name,
            sellingPrice = from.sellingPrice,
            purchasePrice = from.purchasePrice,
            amount = from.amount,
            stockId = from.stockId,
            unit = from.unit,
            merchantId = from.merchantId,
            createdOn = from.createdOn,
            updatedOn = from.updatedOn,
            categoryId = from.categoryId
        )

    fun dtoToEntityList(from: List<ProductDTO>): List<ProductEntity> =
        ArrayList<ProductEntity>(from.size).apply {
            from.forEach {
                add(dtoToEntity(it))
            }
        }

    fun entityToDomain(from: ProductEntity): Product =
        Product(
            id = from.id,
            barcode = from.barcode,
            name = from.name,
            sellingPrice = from.sellingPrice,
            purchasePrice = from.purchasePrice,
            amount = from.amount,
            stockId = from.stockId,
            unit = from.unit,
            merchantId = from.merchantId,
            createdOn = from.createdOn,
            updatedOn = from.updatedOn,
            categoryId = from.categoryId,
            basketCount = from.basketCount
        )

    fun entityToDomainList(from: List<ProductEntity>): List<Product> =
        ArrayList<Product>(from.size).apply {
            from.forEach {
                add(entityToDomain(it))
            }
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

fun List<Product>.toEntity(): List<ProductEntity> =
    this.map {
        it.toEntity()
    }