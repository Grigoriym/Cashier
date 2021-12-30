package com.grappim.products.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.zhuinden.flowcombinetuplekt.combineTuple
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase
) : ViewModel() {

    val categories: StateFlow<List<Category>> =
        getCategoryListUseCase.execute2(
            GetCategoryListUseCase.Params()
        ).stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = emptyList()
        )

    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query.asStateFlow()

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex: StateFlow<Int>
        get() = _selectedIndex.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(
        null
    )

    val products: Flow<List<Product>> = combineTuple(
        query,
        _selectedCategory
    ).flatMapLatest { (query, category) ->
        getProductsByQueryUseCase.invoke(
            GetProductsByQueryUseCase.Params(
                category,
                query
            )
        )
    }

    fun onBackPressed() {
//        navigator.popBackStack()
    }

    fun showCreateProduct() {
//        navigator.navigate(ProductsFragmentDirections.actionProductsToCreateProduct())
    }

    fun showEditProduct(product: Product) {
//        navigator.navigate(ProductsFragmentDirections.actionProductsToEditProduct(product = product))
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
    }

    fun setCategory(category: Category, index: Int) {
        viewModelScope.launch {
            _selectedCategory.value = category
            _selectedIndex.value = index
        }
    }

}