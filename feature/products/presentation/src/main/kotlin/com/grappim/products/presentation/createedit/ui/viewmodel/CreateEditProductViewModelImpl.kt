package com.grappim.products.presentation.createedit.ui.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.PriceCalculationsUtils
import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalOne
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.Product
import com.grappim.domain.model.ProductUnit
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.products.domain.interactor.createProduct.CreateProductParams
import com.grappim.feature.products.domain.interactor.createProduct.CreateProductUseCase
import com.grappim.feature.products.domain.interactor.editProduct.EditProductParams
import com.grappim.feature.products.domain.interactor.editProduct.EditProductUseCase
import com.grappim.product_category.domain.interactor.getCategoryList.GetCategoryListInteractorParams
import com.grappim.product_category.domain.interactor.getCategoryList.GetCategoryListUseCase
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.products.presentation.model.CreateEditFlow
import com.zhuinden.flowcombinetuplekt.combineTuple
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CreateEditProductViewModelImpl @AssistedInject constructor(
    private val createProductUseCase: CreateProductUseCase,
    private val priceCalculationsUtils: PriceCalculationsUtils,
    private val editProductUseCase: EditProductUseCase,
    getCategoryListUseCase: GetCategoryListUseCase,
    private val generalStorage: GeneralStorage,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    @Assisted private val createEditFlow: CreateEditFlow,
    @Assisted private val productToEdit: Product?
) : CreateEditProductViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            createEditFlow: CreateEditFlow,
            productToEdit: Product?
        ): CreateEditProductViewModelImpl
    }

    override val productUnits = flow {
        emit(ProductUnit.values().toList())
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = emptyList()
    )

    override val categoriesFlow: StateFlow<List<ProductCategory>> =
        getCategoryListUseCase
            .categoriesInEditProduct(GetCategoryListInteractorParams(false))
            .map { list ->
                if (productToEdit?.categoryId != null) {
                    val found = list.find { category ->
                        category.id == productToEdit.categoryId
                    }
                    selectedCategory.value = found
                }
                list
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    override val selectedCategory = MutableStateFlow<ProductCategory?>(null)

    override val productName = MutableStateFlow<String>(
        productToEdit?.name ?: ""
    )

    override val selectedUnit = MutableStateFlow<ProductUnit>(
        productToEdit?.unit ?: ProductUnit.PIECE
    )

    override val barcode = MutableStateFlow(
        productToEdit?.barcode ?: ""
    )

    override val purchasePrice = MutableStateFlow<String>(
        if (productToEdit?.purchasePrice != null) {
            dfSimple.format(productToEdit.purchasePrice)
        } else {
            ""
        }
    )

    override val sellingPrice = MutableStateFlow<String>(
        if (productToEdit?.sellingPrice != null) {
            dfSimple.format(productToEdit.sellingPrice)
        } else {
            ""
        }
    )

    override val markup = MutableStateFlow(
        dfSimple.format(
            priceCalculationsUtils.calculateOnChangingSellingPrice(
                sellingPrice.value.asBigDecimal(),
                purchasePrice.value.asBigDecimal()
            )
        )
    )

    override val amount = MutableStateFlow(
        productToEdit?.amount ?: bigDecimalOne()
    )

    override val dropDownExpanded = MutableStateFlow(false)

    override val amountAndUnit = combineTuple(
        amount,
        selectedUnit
    ).map { (amount, unit) ->
        val formattedAmount = dfSimple.format(amount)
        val readableUnit = unit.value
        "$formattedAmount $readableUnit"
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = "${productToEdit?.amount ?: bigDecimalOne()} ${productToEdit?.unit ?: ProductUnit.PIECE}"
    )

    override fun dismissDropDown() {
        dropDownExpanded.value = false
    }

    override fun onDropDownExpand() {
        dropDownExpanded.value = true
    }

    override fun goToScanner() {
        flowRouter.goToScanner()
    }

    override fun selectCategory(newCategory: ProductCategory) {
        if (newCategory.isCreateCategory()) {
            flowRouter.goToCreateCategoryFromProduct()
        } else {
            selectedCategory.value = newCategory
            dropDownExpanded.value = false
        }
    }

    override fun setProductName(newName: String) {
        productName.value = newName
    }

    override fun setProductUnit(newUnit: ProductUnit) {
        selectedUnit.value = newUnit
    }

    override fun setBarcode(newBarcode: String) {
        barcode.value = newBarcode
    }

    override fun setPurchasePrice(newPrice: String) {
        purchasePrice.value = newPrice
        val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
            purchasePrice.value.asBigDecimal(),
            markup.value.asBigDecimal()
        )
        sellingPrice.value = dfSimple.format(newSellingPrice)
    }

    override fun setSellingPrice(newPrice: String) {
        sellingPrice.value = newPrice
        priceCalculationsUtils.calculateOnChangingSellingPrice(
            sellingPrice.value.asBigDecimal(),
            purchasePrice.value.asBigDecimal()
        ) { newMarkup ->
            markup.value = dfSimple.format(newMarkup)
        }
    }

    override fun setMarkup(newMarkup: String) {
        if (newMarkup.isDigitsOnly()) {
            markup.value = newMarkup
            val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
                purchasePrice = purchasePrice.value.asBigDecimal(),
                markup = markup.value.asBigDecimal()
            )
            sellingPrice.value = dfSimple.format(newSellingPrice)
        }
    }

    override fun addQuantity() {
        val currentAmount = amount.value
        amount.value = currentAmount + bigDecimalOne()
    }

    override fun subtractQuantity() {
        val currentAmount = amount.value
        amount.value = currentAmount + bigDecimalOne()
    }

    private fun createProduct() {
        viewModelScope.launch {
            _loading.value = true
            val result = createProductUseCase.execute(
                CreateProductParams(
                    name = productName.value,
                    barcode = barcode.value,
                    sellingPrice = sellingPrice.value.asBigDecimal(),
                    purchasePrice = purchasePrice.value.asBigDecimal(),
                    stockId = generalStorage.stockId,
                    merchantId = generalStorage.getMerchantId(),
                    unit = selectedUnit.value.value,
                    amount = amount.value,
                    categoryName = selectedCategory.value?.name ?: "",
                    categoryId = selectedCategory.value?.id ?: 0
                )
            )
            _loading.value = true
            when (result) {
                is Try.Success -> {
                    onBackPressed()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    private fun editProduct() {
        viewModelScope.launch {
            val result = editProductUseCase.execute(
                EditProductParams(
                    name = productName.value,
                    barcode = barcode.value,
                    sellingPrice = sellingPrice.value.asBigDecimal(),
                    purchasePrice = purchasePrice.value.asBigDecimal(),
                    amount = amount.value,
                    unit = selectedUnit.value,
                    productMerchantId = productToEdit?.merchantId ?: "",
                    productCreatedOn = productToEdit?.createdOn ?: "",
                    productId = productToEdit?.id ?: 0,
                    productStockId = productToEdit?.stockId ?: "",
                    categoryId = selectedCategory.value?.id ?: 0,
                    category = selectedCategory.value?.name ?: ""
                )
            )
            when (result) {
                is Try.Success -> {
                    onBackPressed()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    override fun createEditProduct() {
        when (createEditFlow) {
            CreateEditFlow.CREATE -> {
                createProduct()
            }
            CreateEditFlow.EDIT -> {
                editProduct()
            }
        }
    }
}
