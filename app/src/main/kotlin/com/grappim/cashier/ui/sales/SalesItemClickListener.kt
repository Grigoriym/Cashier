package com.grappim.cashier.ui.sales

import com.grappim.domain.model.product.Product

interface SalesItemClickListener {
    fun addProduct(product: Product)
    fun removeProduct(product: Product)
}