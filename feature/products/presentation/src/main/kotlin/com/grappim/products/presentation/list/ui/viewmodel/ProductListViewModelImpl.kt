package com.grappim.products.presentation.list.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.cashier.feature.productcategory.domain.interactor.getCategoryList.GetCategoryListInteractorParams
import com.grappim.cashier.feature.productcategory.domain.interactor.getCategoryList.GetCategoryListUseCase
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.interactor.getProductsByQuery.GetProductsByQueryParams
import com.grappim.feature.products.domain.interactor.getProductsByQuery.GetProductsByQueryUseCase
import com.grappim.products.presentation.BundleArgsKeys
import com.grappim.products.presentation.model.CreateEditFlow
import com.zhuinden.flowcombinetuplekt.combineTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModelImpl @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase
) : ProductListViewModel() {

    override val categories: StateFlow<List<ProductCategory>> =
        getCategoryListUseCase.getSimpleCategoryList(
            GetCategoryListInteractorParams()
        ).stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = emptyList()
        )

    override val query = MutableStateFlow("")

    override val selectedIndex = MutableStateFlow(0)

    private val selectedCategory = MutableStateFlow<ProductCategory?>(null)

    override val products: Flow<List<Product>> = combineTuple(
        query,
        selectedCategory
    ).flatMapLatest { (query, category) ->
        getProductsByQueryUseCase.execute(
            GetProductsByQueryParams(
                category,
                query
            )
        )
    }

    override fun showCreateProduct() {
        val args = Bundle(1).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.CREATE)
        }
        flowRouter.goToCreateProduct(args)
    }

    override fun showEditProduct(product: Product) {
        val args = Bundle(2).apply {
//            putSerializable(BundleArgsKeys.ARG_KEY_PRODUCT, product)
            putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.EDIT)
        }
        flowRouter.goToEditProduct(args)
    }

    override fun searchProducts(newQuery: String) {
        viewModelScope.launch {
            query.value = newQuery
        }
    }

    override fun setCategory(category: ProductCategory, index: Int) {
        viewModelScope.launch {
            selectedCategory.value = category
            selectedIndex.value = index
        }
    }
}
