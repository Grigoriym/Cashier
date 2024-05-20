package com.grappim.cashier.feature.productcategory.presentation.createedit.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.cashier.feature.productcategory.presentation.createedit.model.CreateEditCategoryData
import kotlinx.coroutines.flow.StateFlow

abstract class CreateEditProductCategoryViewModel : BaseViewModel() {

    abstract val categoryData: StateFlow<CreateEditCategoryData>

    abstract fun setName(name: String)

    abstract fun createEditCategory()
}
