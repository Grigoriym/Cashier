package com.grappim.products.list.ui.viewmodel

import com.grappim.core.BaseViewModel
import com.grappim.domain.model.product.Product
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class ProductListViewModel : BaseViewModel() {

    abstract val categories: StateFlow<List<ProductCategory>>
    abstract val query: StateFlow<String>
    abstract val selectedIndex: StateFlow<Int>
    abstract val products: Flow<List<Product>>

    abstract fun onBackPressed()
    abstract fun showCreateProduct()
    abstract fun closeFlow()
    abstract fun showEditProduct(product: Product)
    abstract fun searchProducts(newQuery: String)
    abstract fun setCategory(category: ProductCategory, index: Int)
}