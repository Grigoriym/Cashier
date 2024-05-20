package com.grappim.feature.waybill.presentation.ui.scanner.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.core.SingleLiveEvent
import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductBarcodeParams
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductByBarcodeUseCase
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeUseCase
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
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

    private fun findProductByBarcode(barcode: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = getProductByBarcodeUseCase.execute(
                GetProductBarcodeParams(
                    barcode
                )
            )
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

    fun checkProductInWaybill(barcode: String) {
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
                    _waybillProduct.value =
                        WaybillProductByBarcodeResult.SuccessResult(result.result)
                }

                is Try.Error -> {
                    findProductByBarcode(barcode)
                }
            }
        }
    }
}
