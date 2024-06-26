package com.grappim.products.presentation.createedit.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.domain.model.ProductUnit
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

abstract class CreateEditProductViewModel : BaseViewModel() {

    abstract val productUnits: StateFlow<List<ProductUnit>>
    abstract val categoriesFlow: StateFlow<List<ProductCategory>>
    abstract val selectedCategory: StateFlow<ProductCategory?>
    abstract val productName: StateFlow<String>
    abstract val selectedUnit: StateFlow<ProductUnit>
    abstract val barcode: StateFlow<String>
    abstract val purchasePrice: StateFlow<String>
    abstract val sellingPrice: StateFlow<String>
    abstract val markup: StateFlow<String>
    abstract val amount: StateFlow<BigDecimal>
    abstract val amountAndUnit: StateFlow<String>
    abstract val dropDownExpanded: StateFlow<Boolean>

    abstract fun dismissDropDown()
    abstract fun onDropDownExpand()

    abstract fun goToScanner()

    abstract fun selectCategory(newCategory: ProductCategory)
    abstract fun setProductName(newName: String)
    abstract fun setProductUnit(newUnit: ProductUnit)
    abstract fun setPurchasePrice(newPrice: String)
    abstract fun setBarcode(newBarcode: String)
    abstract fun setSellingPrice(newPrice: String)
    abstract fun setMarkup(newMarkup: String)
    abstract fun addQuantity()
    abstract fun subtractQuantity()
    abstract fun createEditProduct()
}
