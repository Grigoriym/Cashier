package com.grappim.waybill.ui.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WaybillScannerViewModel @Inject constructor(
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase
) : ViewModel() {

    private val _waybillProduct = com.grappim.core.SingleLiveEvent<Try<WaybillProduct>>()
    val waybillProduct: LiveData<Try<WaybillProduct>>
        get() = _waybillProduct

    private val _product = com.grappim.core.SingleLiveEvent<Try<Product>>()
    val product: LiveData<Try<Product>>
        get() = _product

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _product.value = Try.Loading
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
            _waybillProduct.value = Try.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                GetWaybillProductByBarcodeUseCase.Params(
                    barcode = barcode,
                    waybillId = waybillId
                )
            ).collect {
                when (it) {
                    is Try.Success -> {
                        _waybillProduct.value = it
                    }
                    is Try.Error -> {
                        findProductByBarcode(barcode)
                    }
                }
            }
        }
    }
}