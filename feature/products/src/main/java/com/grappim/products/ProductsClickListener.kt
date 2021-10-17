package com.grappim.products

import com.grappim.domain.model.product.Product

interface ProductsClickListener {

    fun onProductClick(product: Product)
}