package com.grappim.product_category.presentation.create_edit.ui.viewmodel

import com.grappim.core.base.BaseViewModel2
import com.grappim.product_category.presentation.create_edit.model.CreateEditCategoryData
import kotlinx.coroutines.flow.StateFlow

abstract class CreateEditProductCategoryViewModel : BaseViewModel2() {

    abstract val categoryData: StateFlow<CreateEditCategoryData>

    abstract fun setName(name: String)

    abstract fun createEditCategory()

}