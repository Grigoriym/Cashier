package com.grappim.products.list.ui

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.BaseViewModel
import com.grappim.domain.interactor.products.GetCategoryListInteractor
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.products.root.di.ProductsScreenNavigator
import com.zhuinden.flowcombinetuplekt.combineTuple
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    getCategoryListInteractor: GetCategoryListInteractor,
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase,
    private val productsScreenNavigator: ProductsScreenNavigator
) : BaseViewModel() {

    val categories: StateFlow<List<Category>> =
        getCategoryListInteractor.getSimpleCategoryList(
            GetCategoryListInteractor.Params()
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
        productsScreenNavigator.goBack()
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