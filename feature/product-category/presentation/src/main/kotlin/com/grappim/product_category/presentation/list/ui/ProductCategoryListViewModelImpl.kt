package com.grappim.product_category.presentation.list.ui

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.common.lce.withoutParams
import com.grappim.product_category.domain.interactor.GetProductCategoriesUseCase
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProductCategoryListViewModelImpl @Inject constructor(
    private val productCategoryScreenNavigator: ProductCategoryScreenNavigator,
    getProductCategoriesUseCase: GetProductCategoriesUseCase
) : ProductCategoryListViewModel() {

    override fun onBackPressed() {
        productCategoryScreenNavigator.goBack()
    }

    override val categories: StateFlow<List<ProductCategory>> =
        getProductCategoriesUseCase.invoke(withoutParams())
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    override fun goToCategoryDetails(productCategory: ProductCategory) {
        productCategoryScreenNavigator.goToProductCategoryDetails()
    }

    override fun goToCategoryCreate() {
        productCategoryScreenNavigator.goToCreateProductCategory()
    }

}