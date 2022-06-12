package com.grappim.waybill.ui.scanner.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.core.SingleLiveEvent
import com.grappim.core.base.BaseViewModel
import com.grappim.domain.interactor.products.GetProductBarcodeParams
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeParams
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.repository.local.WaybillLocalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WaybillScannerViewModel @Inject constructor(
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val waybillLocalRepository: WaybillLocalRepository
) : BaseViewModel() {

    private val _waybillProduct = SingleLiveEvent<WaybillProductByBarcodeResult>()
    val waybillProduct: LiveData<WaybillProductByBarcodeResult>
        get() = _waybillProduct

    private val _product = SingleLiveEvent<ProductByBarcodeResult>()
    val product: LiveData<ProductByBarcodeResult>
        get() = _product

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val result = getProductByBarcodeUseCase.execute(GetProductBarcodeParams(barcode))
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    _product.value = ProductByBarcodeResult.SuccessResult(result.result)
                }
                is Try.Error -> {
                    _product.value = ProductByBarcodeResult.ErrorResult(result.result)
                }
            }
        }
    }

    fun checkProductInWaybill(
        barcode: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val result = getWaybillProductByBarcodeUseCase.execute(
                GetWaybillProductByBarcodeParams(
                    barcode = barcode,
                    waybillId = waybillLocalRepository.waybill.id
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    _waybillProduct.value = WaybillProductByBarcodeResult
                            .SuccessResult(result.result)
                }
                is Try.Error -> {
                    findProductByBarcode(barcode)
                }
            }
        }
    }
}