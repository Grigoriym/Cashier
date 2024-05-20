package com.grappim.cashier.feature.productcategory.presentation.list.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.cashier.feature.productcategory.domain.interactor.getProductCategories.GetProductCategoriesUseCase
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.cashier.feature.productcategory.presentation.BundleArgsKeys
import com.grappim.cashier.feature.productcategory.presentation.createedit.model.CreateEditFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProductCategoryListViewModelImpl @Inject constructor(
    getProductCategoriesUseCase: GetProductCategoriesUseCase
) : ProductCategoryListViewModel() {

    override val categories: StateFlow<List<ProductCategory>> =
        getProductCategoriesUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    @Suppress("EmptyFunctionBlock")
    override fun refresh() {}

    override fun goToCategoryDetails(productCategory: ProductCategory) {
        val args = Bundle(2).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_EDIT_CATEGORY, productCategory)
            putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.EDIT)
        }
        flowRouter.goToEditProductCategory(args)
    }

    override fun goToCategoryCreate() {
        val args = Bundle(1).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.CREATE)
        }
        flowRouter.goToCreateProductCategory(args)
    }
}
