package com.grappim.product_category.presentation.create_edit.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.product_category.domain.interactor.createProductCateogry.CreateProductCategoryParams
import com.grappim.product_category.domain.interactor.createProductCateogry.CreateProductCategoryUseCase
import com.grappim.product_category.domain.interactor.editProductCategory.EditProductCategoryParams
import com.grappim.product_category.domain.interactor.editProductCategory.EditProductCategoryUseCase
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.presentation.create_edit.model.CreateEditCategoryData
import com.grappim.product_category.presentation.create_edit.model.CreateEditFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateEditProductCategoryViewModelImpl @AssistedInject constructor(
    private val createProductCategoryUseCase: CreateProductCategoryUseCase,
    private val editProductCategoryUseCase: EditProductCategoryUseCase,
    @Assisted private val createEditFlow: CreateEditFlow,
    @Assisted private val productCategory: ProductCategory?
) : CreateEditProductCategoryViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            createEditFlow: CreateEditFlow,
            productCategory: ProductCategory?
        ): CreateEditProductCategoryViewModelImpl
    }

    override val categoryData: MutableStateFlow<CreateEditCategoryData> =
        MutableStateFlow(
            if (productCategory == null) {
                CreateEditCategoryData.empty()
            } else {
                CreateEditCategoryData(
                    categoryName = productCategory.name
                )
            }
        )

    override fun setName(name: String) {
        val oldValue = categoryData.value
        categoryData.value = oldValue.copy(
            categoryName = name
        )
    }

    override fun createEditCategory() {
        when (createEditFlow) {
            CreateEditFlow.CREATE -> {
                createCategory()
            }
            CreateEditFlow.EDIT -> {
                editCategory()
            }
        }
    }

    private fun doOnSuccess() {
        flowRouter.onBackPressed()
    }

    private fun createCategory() {
        viewModelScope.launch {
            _loading.value = true
            val result = createProductCategoryUseCase.execute(
                CreateProductCategoryParams(
                    name = categoryData.value.categoryName
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    doOnSuccess()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    private fun editCategory() {
        viewModelScope.launch {
            _loading.value = true
            val result = editProductCategoryUseCase.execute(
                EditProductCategoryParams(
                    newName = categoryData.value.categoryName,
                    productCategory = requireNotNull(productCategory)
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    doOnSuccess()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

}