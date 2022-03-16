package com.grappim.waybill.ui.scanner.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.core.base.BaseViewModel2
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WaybillScannerViewModel @Inject constructor(
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val waybillLocalRepository: WaybillLocalRepository
) : BaseViewModel2() {

    private val _waybillProduct = SingleLiveEvent<Try<WaybillProduct>>()
    val waybillProduct: LiveData<Try<WaybillProduct>>
        get() = _waybillProduct

    private val _product = SingleLiveEvent<Try<Product>>()
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
        barcode: String
    ) {
        viewModelScope.launch {
            _waybillProduct.value = Try.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                GetWaybillProductByBarcodeUseCase.Params(
                    barcode = barcode,
                    waybillId = waybillLocalRepository.waybill.id
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