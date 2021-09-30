package com.grappim.cashier.ui.products.create

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalOne
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.date_time.DateTimeUtils
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.domain.storage.GeneralStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CreateEditProductViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase,
    private val priceCalculationsUtils: com.grappim.calculations.PriceCalculationsUtils,
    private val editProductUseCase: EditProductUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val generalStorage: GeneralStorage,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : ViewModel() {

    private val _unit = MutableLiveData<ProductUnit>(
        ProductUnit.values().first()
    )
    val unit: LiveData<ProductUnit>
        get() = _unit

    private val _quantity = MutableLiveData(
        dfSimple.format(bigDecimalOne())
    )
    val quantity: LiveData<String>
        get() = _quantity

    private val _createProduct = MutableLiveData<Result<Unit>>()
    val createProduct: LiveData<Result<Unit>>
        get() = _createProduct

    private val _sellingPrice = MutableLiveData<String>("0")
    val sellingPrice: LiveData<String>
        get() = _sellingPrice

    private val _purchasePrice = MutableLiveData<String>("0")
    val purchasePrice: LiveData<String>
        get() = _purchasePrice

    private val _markup = MutableLiveData<String>("0")
    val markup: LiveData<String>
        get() = _markup

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _selectedCategory = MutableLiveData<Category?>()
    val selectedCategory: LiveData<Category?>
        get() = _selectedCategory

    init {
        getCategories()
    }

    fun setUnit(unit: ProductUnit) {
        _unit.value = unit
    }

    fun setQuantity(quantity: BigDecimal) {
        _quantity.value = dfSimple.format(quantity)
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryListUseCase.invoke(GetCategoryListUseCase.Params(false))
                .collect {
                    if (it is Result.Success) {
                        _categories.value = it.data!!
                    }
                }
        }
    }

    fun minusQuantity() {
        val quantityToChange = _quantity.value!!.asBigDecimal()
        if (quantityToChange > bigDecimalOne()) {
            _quantity.value = dfSimple.format(quantityToChange - bigDecimalOne())
        }
    }

    fun plusQuantity() {
        val quantityToChange = _quantity.value!!.asBigDecimal()
        _quantity.value = dfSimple.format(quantityToChange + bigDecimalOne())
    }

    @MainThread
    fun setCategory(position: Int) {
        _selectedCategory.value = _categories.value?.get(position)
    }

    @MainThread
    fun onSalesPriceChanged(salesPrice: String) {
        _sellingPrice.value = dfSimple.format(salesPrice.asBigDecimal())
        priceCalculationsUtils.calculateOnChangingSellingPrice(
            _sellingPrice.value!!.asBigDecimal(),
            _purchasePrice.value!!.asBigDecimal()
        ) { markup ->
            _markup.value = dfSimple.format(markup)
        }
    }

    @MainThread
    fun onCostPriceChanged(costPrice: String) {
        _purchasePrice.value = dfSimple.format(costPrice.asBigDecimal())
        val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
            _purchasePrice.value!!.asBigDecimal(),
            _markup.value!!.asBigDecimal()
        )
        _sellingPrice.value = dfSimple.format(newSellingPrice)
    }

    @MainThread
    fun onExtraPriceChanged(extraPrice: String) {
        _markup.value = dfSimple.format(extraPrice.asBigDecimal())
        val newSellingPrice = priceCalculationsUtils.calculateOnChangingMarkup(
            _purchasePrice.value!!.asBigDecimal(),
            _markup.value!!.asBigDecimal()
        )
        _sellingPrice.value = dfSimple.format(newSellingPrice)
    }

    @MainThread
    fun setExtraPrice() {
        priceCalculationsUtils.calculateOnChangingSellingPrice(
            _sellingPrice.value!!.asBigDecimal(),
            _purchasePrice.value!!.asBigDecimal()
        ) { markup ->
            _markup.value = dfSimple.format(markup)
        }
    }

    @MainThread
    fun setSellingPrice(sellingPrice: BigDecimal) {
        onSalesPriceChanged(dfSimple.format(sellingPrice))
    }

    @MainThread
    fun setPurchasePrice(purchasePrice: BigDecimal) {
        onCostPriceChanged(dfSimple.format(purchasePrice))
    }

    @MainThread
    fun editCreateProduct(
        createEditFlow: CreateEditFlow,
        name: String,
        barcode: String,
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal,
        amount: BigDecimal,
        unit: ProductUnit,
        product: Product?
    ) {
        when (createEditFlow) {
            CreateEditFlow.EDIT -> {
                viewModelScope.launch {
                    editProductUseCase.invoke(
                        EditProductUseCase.Params(
                            name = name,
                            barcode = barcode,
                            sellingPrice = sellingPrice,
                            purchasePrice = purchasePrice,
                            amount = amount,
                            unit = unit,
                            productMerchantId = product?.merchantId ?: "",
                            productCreatedOn = product?.createdOn ?: "",
                            productId = product?.id ?: 0,
                            productStockId = product?.stockId ?: "",
                            categoryId = _selectedCategory.value?.id ?: 0,
                            category = _selectedCategory.value?.name ?: ""
                        )
                    ).collect {
                        _createProduct.value = it
                    }
                }
            }
            CreateEditFlow.CREATE -> {
                viewModelScope.launch {
                    createProductUseCase.invoke(
                        CreateProductUseCase.Params(
                            name = name,
                            barcode = barcode,
                            sellingPrice = sellingPrice,
                            purchasePrice = purchasePrice,
                            stockId = generalStorage.getStockId(),
                            merchantId = generalStorage.getMerchantId(),
                            unit = unit.value,
                            amount = amount,
                            createdOn = DateTimeUtils.getNowFullDate(),
                            updatedOn = DateTimeUtils.getNowFullDate(),
                            categoryName = _selectedCategory.value?.name ?: "",
                            categoryId = _selectedCategory.value?.id ?: 0
                        )
                    ).collect {
                        _createProduct.value = it
                    }
                }
            }
        }
    }
}