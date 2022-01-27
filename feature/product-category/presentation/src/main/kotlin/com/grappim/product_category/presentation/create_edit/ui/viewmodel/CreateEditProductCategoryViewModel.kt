package com.grappim.product_category.presentation.create_edit.ui.viewmodel

import com.grappim.core.BaseViewModel
import com.grappim.product_category.presentation.create_edit.model.CreateEditCategoryData
import kotlinx.coroutines.flow.StateFlow

abstract class CreateEditProductCategoryViewModel : BaseViewModel() {

    abstract val categoryData: StateFlow<CreateEditCategoryData>

    abstract fun onBackPressed()

    abstract fun setName(name: String)

    abstract fun createEditCategory()

}