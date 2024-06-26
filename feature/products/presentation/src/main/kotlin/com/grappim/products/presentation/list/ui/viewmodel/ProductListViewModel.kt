package com.grappim.products.presentation.list.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class ProductListViewModel : BaseViewModel() {

    abstract val categories: StateFlow<List<ProductCategory>>
    abstract val query: StateFlow<String>
    abstract val selectedIndex: StateFlow<Int>
    abstract val products: Flow<List<Product>>

    abstract fun showCreateProduct()
    abstract fun showEditProduct(product: Product)
    abstract fun searchProducts(newQuery: String)
    abstract fun setCategory(category: ProductCategory, index: Int)
}
