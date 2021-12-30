package com.grappim.products.create

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.PriceCalculationsUtils
import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalOne
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.date_time.DateTimeUtils
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.domain.storage.GeneralStorage
import com.zhuinden.flowcombinetuplekt.combineTuple
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat

class CreateEditProductViewModel @AssistedInject constructor(
    private val createProductUseCase: CreateProductUseCase,
    private val priceCalculationsUtils: PriceCalculationsUtils,
    private val editProductUseCase: EditProductUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val generalStorage: GeneralStorage,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    @Assisted private val createEditFlow: CreateEditFlow,
    @Assisted private val productToEdit: Product?,
    @Assisted private val scannedBarcode: String?
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            createEditFlow: CreateEditFlow,
            productToEdit: Product?,
            scannedBarcode: String?
        ): CreateEditProductViewModel
    }

    val productUnits = flow {
        emit(ProductUnit.values().toList())
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = emptyList()
    )

    val categoriesFlow =
        getCategoryListUseCase
            .execute2(GetCategoryListUseCase.Params(false))
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = emptyList()
            )

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?>
        get() = _selectedCategory.asStateFlow()

    private val _productName = MutableStateFlow<String>(
        productToEdit?.name ?: ""
    )
    val productName: StateFlow<String>
        get() = _productName.asStateFlow()

    private val _selectedUnit = MutableStateFlow<ProductUnit>(
        productToEdit?.unit ?: ProductUnit.PIECE
    )
    val selectedUnit: StateFlow<ProductUnit>
        get() = _selectedUnit.asStateFlow()

    private val _barcode = MutableStateFlow(
        productToEdit?.barcode ?: ""
    )
    val barcode: StateFlow<String>
        get() = _barcode.asStateFlow()

    private val _purchasePrice = MutableStateFlow<String>(
        if (productToEdit?.purchasePrice != null) {
            dfSimple.format(productToEdit.purchasePrice)
        } else {
            ""
        }
    )
    val purchasePrice: StateFlow<String>
        get() = _purchasePrice.asStateFlow()

    private val _sellingPrice = MutableStateFlow<String>(
        if (productToEdit?.sellingPrice != null) {
            dfSimple.format(productToEdit.sellingPrice)
        } else {
            ""
        }
    )
    val sellingPrice: StateFlow<String>
        get() = _sellingPrice.asStateFlow()

    private val _markup = MutableStateFlow(
        dfSimple.format(
            priceCalculationsUtils.calculateOnChangingSellingPrice(
                _sellingPrice.value.asBigDecimal(),
                _purchasePrice.value.asBigDecimal()
            )
        )
    )
    val markup: StateFlow<String>
        get() = _markup.asStateFlow()

    private val _amount = MutableStateFlow(
        productToEdit?.amount ?: bigDecimalOne()
    )
    val amount: StateFlow<BigDecimal>
        get() = _amount.asStateFlow()

    private val _dropDownExpanded = MutableStateFlow(false)
    val dropDownExpanded: StateFlow<Boolean>
        get() = _dropDownExpanded

    val amountAndUnit = combineTuple(
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

    private val _createProduct = MutableStateFlow<Try<Unit>>(
        Try.Initial
    )
    val createProduct: StateFlow<Try<Unit>>
        get() = _createProduct.asStateFlow()

    fun onBackPressed() {
//        navigator.popBackStack()
    }

    fun dismissDropDown() {
        _dropDownExpanded.value = false
    }

    fun onDropDownExpand() {
        _dropDownExpanded.value = true
    }

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
        _dropDownExpanded.value = false
    }

    fun setProductName(name: String) {
        _productName.value = name
    }

    fun setProductUnit(unit: ProductUnit) {
        _selectedUnit.value = unit
    }

    fun setBarcode(barcode: String) {
        _barcode.value = barcode
    }

    fun setPurchasePrice(price: String) {
        _purchasePrice.value = price
        val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
            _purchasePrice.value.asBigDecimal(),
            _markup.value.asBigDecimal()
        )
        _sellingPrice.value = dfSimple.format(newSellingPrice)
    }

    fun setSellingPrice(price: String) {
        _sellingPrice.value = price
        priceCalculationsUtils.calculateOnChangingSellingPrice(
            _sellingPrice.value.asBigDecimal(),
            _purchasePrice.value.asBigDecimal()
        ) { markup ->
            _markup.value = dfSimple.format(markup)
        }
    }

    fun setMarkup(markup: String) {
        if (markup.isDigitsOnly()) {
            _markup.value = markup
            val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
                _purchasePrice.value.asBigDecimal(),
                _markup.value.asBigDecimal()
            )
            _sellingPrice.value = dfSimple.format(newSellingPrice)
        }
    }

    fun addQuantity() {
        val currentAmount = _amount.value
        _amount.value = currentAmount + bigDecimalOne()
    }

    fun subtractQuantity() {
        val currentAmount = _amount.value
        _amount.value = currentAmount + bigDecimalOne()
    }

    fun createEditProduct() {
        when (createEditFlow) {
            CreateEditFlow.CREATE -> {
                viewModelScope.launch {
                    createProductUseCase.invoke(
                        CreateProductUseCase.Params(
                            name = productName.value,
                            barcode = barcode.value,
                            sellingPrice = sellingPrice.value.asBigDecimal(),
                            purchasePrice = purchasePrice.value.asBigDecimal(),
                            stockId = generalStorage.stockId,
                            merchantId = generalStorage.getMerchantId(),
                            unit = selectedUnit.value.value,
                            amount = amount.value,
                            createdOn = DateTimeUtils.getNowFullDate(),
                            updatedOn = DateTimeUtils.getNowFullDate(),
                            categoryName = _selectedCategory.value?.name ?: "",
                            categoryId = _selectedCategory.value?.id ?: 0
                        )
                    ).collect {
                        when (it) {
                            is Try.Success -> {
                                onBackPressed()
                            }
                        }
                        _createProduct.value = it
                    }
                }
            }
            CreateEditFlow.EDIT -> {
                viewModelScope.launch {
                    editProductUseCase.invoke(
                        EditProductUseCase.Params(
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
                            categoryId = _selectedCategory.value?.id ?: 0,
                            category = _selectedCategory.value?.name ?: ""
                        )
                    ).collect {
                        when (it) {
                            is Try.Success -> {
                                onBackPressed()
                            }
                        }
                        _createProduct.value = it
                    }
                }
            }
        }
    }
}