package com.grappim.cashier.ui.sales

import com.grappim.cashier.data.db.entity.ProductEntity

interface SalesItemClickListener {
    fun addProduct(productEntity: ProductEntity)
    fun removeProduct(productEntity: ProductEntity)
}