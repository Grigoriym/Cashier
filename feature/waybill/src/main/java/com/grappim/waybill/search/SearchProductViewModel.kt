package com.grappim.waybill.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.core.SingleLiveEvent
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.products.GetProductsUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase
) : ViewModel() {

    init {
        getProducts()
    }

    private val _waybillProduct = com.grappim.core.SingleLiveEvent<Result<WaybillProduct>>()
    val waybillProduct: LiveData<Result<WaybillProduct>>
        get() = _waybillProduct

    private val _products: com.grappim.core.SingleLiveEvent<List<Product>> =
        com.grappim.core.SingleLiveEvent()
    val products: LiveData<List<Product>>
        get() = _products

    private val _product = com.grappim.core.SingleLiveEvent<Result<Product>>()
    val product: LiveData<Result<Product>>
        get() = _product

    fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase.invoke(withoutParams())
                .collect {
                    _products.value = it
                }
        }
    }

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
        barcode: String,
        waybillId: Int
    ) {
        viewModelScope.launch {
            _waybillProduct.value = Result.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                GetWaybillProductByBarcodeUseCase.Params(
                    barcode = barcode,
                    waybillId = waybillId
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        _waybillProduct.value = it
                    }
                    is Result.Error -> {
                        findProductByBarcode(barcode)
                    }
                }
            }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            searchProductsUseCase.invoke(SearchProductsUseCase.Params(query)).collect {
                _products.value = it
            }
        }
    }
}