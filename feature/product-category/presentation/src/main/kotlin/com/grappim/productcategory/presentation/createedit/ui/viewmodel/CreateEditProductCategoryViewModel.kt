package com.grappim.productcategory.presentation.createedit.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.productcategory.presentation.createedit.model.CreateEditCategoryData
import kotlinx.coroutines.flow.StateFlow

abstract class CreateEditProductCategoryViewModel : BaseViewModel() {

    abstract val categoryData: StateFlow<CreateEditCategoryData>

    abstract fun setName(name: String)

    abstract fun createEditCategory()

}
