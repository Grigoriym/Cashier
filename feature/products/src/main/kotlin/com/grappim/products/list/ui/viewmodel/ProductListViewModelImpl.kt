package com.grappim.products.list.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.products.GetCategoryListInteractor
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.model.product.Product
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.products.BundleArgsKeys
import com.grappim.products.model.CreateEditFlow
import com.grappim.products.root.di.ProductsScreenNavigator
import com.zhuinden.flowcombinetuplekt.combineTuple
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModelImpl @Inject constructor(
    getCategoryListInteractor: GetCategoryListInteractor,
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase,
    private val productsScreenNavigator: ProductsScreenNavigator
) : ProductListViewModel() {

    override val categories: StateFlow<List<ProductCategory>> =
        getCategoryListInteractor.getSimpleCategoryList(
            GetCategoryListInteractor.Params()
        ).stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = emptyList()
        )

    override val query = MutableStateFlow("")

    override val selectedIndex = MutableStateFlow(0)

    private val _selectedCategory = MutableStateFlow<ProductCategory?>(null)

    override val products: Flow<List<Product>> = combineTuple(
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

    override fun onBackPressed() {
        productsScreenNavigator.goBack()
    }

    override fun showCreateProduct() {
        productsScreenNavigator.goToCreateProduct()
    }

    override fun showEditProduct(product: Product) {
        val args = Bundle(2).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_PRODUCT, product)
            putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.EDIT)
        }
        productsScreenNavigator.goToEditProduct(args)
    }

    override fun searchProducts(newQuery: String) {
        viewModelScope.launch {
            query.value = newQuery
        }
    }

    override fun setCategory(category: ProductCategory, index: Int) {
        viewModelScope.launch {
            _selectedCategory.value = category
            selectedIndex.value = index
        }
    }

    override fun closeFlow() {
        productsScreenNavigator.activityOnBackPressed()
    }

}