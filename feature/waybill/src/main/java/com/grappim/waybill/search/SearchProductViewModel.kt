package com.grappim.waybill.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.products.GetProductsUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val navigator: Navigator,
    private val waybillLocalRepository: WaybillLocalRepository
) : ViewModel() {

//    init {
//        getProducts()
//    }

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText.asStateFlow()

    val productsFlow = searchText.flatMapConcat {
        searchProductsUseCase.invoke(SearchProductsUseCase.Params(it))
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = emptyList()
    )

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    private val _waybillProduct = MutableStateFlow<Result<WaybillProduct>>(
        Result.Initial
    )
    val waybillProduct: StateFlow<Result<WaybillProduct>>
        get() = _waybillProduct.asStateFlow()

//    private val _products: SingleLiveEvent<List<Product>> =
//        SingleLiveEvent()
//    val products: LiveData<List<Product>>
//        get() = _products

    private val _product = MutableStateFlow<Result<Product>>(
        Result.Initial
    )
    val product: StateFlow<Result<Product>>
        get() = _product.asStateFlow()

//    fun getProducts() {
//        viewModelScope.launch {
//            getProductsUseCase.invoke(withoutParams())
//                .collect {
//                    _products.value = it
//                }
//        }
//    }

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _product.value = Result.Loading
            getProductByBarcodeUseCase.invoke(GetProductByBarcodeUseCase.Params(barcode))
                .collect {
                    _product.value = it
                }
        }
    }

    fun checkProductInWaybill(
        product: Product
    ) {
        viewModelScope.launch {
            _waybillProduct.value = Result.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                GetWaybillProductByBarcodeUseCase.Params(
                    barcode = product.barcode,
                    waybillId = waybillLocalRepository.waybill.id
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        _waybillProduct.value = it
                    }
                    is Result.Error -> {
                        findProductByBarcode(product.barcode)
                    }
                }
            }
        }
    }

//    fun searchProducts(query: String) {
//        viewModelScope.launch {
//            searchProductsUseCase.invoke(SearchProductsUseCase.Params(query)).collect {
//                _products.value = it
//            }
//        }
//    }
}