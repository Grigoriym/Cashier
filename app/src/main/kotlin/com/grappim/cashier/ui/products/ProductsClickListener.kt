package com.grappim.cashier.ui.products

import com.grappim.cashier.data.db.entity.ProductEntity

interface ProductsClickListener {

    fun onProductClick(productEntity: ProductEntity)
}