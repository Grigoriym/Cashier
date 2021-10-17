package com.grappim.bag

import com.grappim.domain.model.product.Product

interface BagItemClickListener {
    fun addProduct(product: Product)
    fun removeProduct(product: Product)
}