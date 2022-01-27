package com.grappim.product_category.presentation.list.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.product_category.domain.interactor.GetProductCategoriesUseCase
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.presentation.BundleArgsKeys
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProductCategoryListViewModelImpl @Inject constructor(
    private val productCategoryScreenNavigator: ProductCategoryScreenNavigator,
    getProductCategoriesUseCase: GetProductCategoriesUseCase
) : ProductCategoryListViewModel() {

    override val categories: StateFlow<List<ProductCategory>> =
        getProductCategoriesUseCase.categoriesFlow()
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    override fun refresh() {

    }

    override fun onBackPressed() {
        productCategoryScreenNavigator.goBack()
    }

    override fun goToCategoryDetails(productCategory: ProductCategory) {
        val args = Bundle(1).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_EDIT_CATEGORY, productCategory)
        }
        productCategoryScreenNavigator.goToEditProductCategory(args)
    }

    override fun goToCategoryCreate() {
        productCategoryScreenNavigator.goToCreateProductCategory()
    }

    override fun closeFlow() {
        productCategoryScreenNavigator.activityOnBackPressed()
    }

}