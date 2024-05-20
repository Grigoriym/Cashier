package com.grappim.cashier.feature.productcategory.presentation.createedit.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.feature.productcategory.domain.interactor.create.CreateProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.create.CreateProductCategoryUseCase
import com.grappim.cashier.feature.productcategory.domain.interactor.edit.EditProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.edit.EditProductCategoryUseCase
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.cashier.feature.productcategory.presentation.createedit.model.CreateEditCategoryData
import com.grappim.cashier.feature.productcategory.presentation.createedit.model.CreateEditFlow
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

    override val categoryData: MutableStateFlow<CreateEditCategoryData> = MutableStateFlow(
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
