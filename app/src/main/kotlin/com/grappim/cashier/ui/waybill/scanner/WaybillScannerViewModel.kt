package com.grappim.cashier.ui.waybill.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.core.platform.SingleLiveEvent
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.products.GetProductByBarcodeUseCase
import com.grappim.cashier.domain.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.cashier.domain.waybill.WaybillProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaybillScannerViewModel @Inject constructor(
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase
) : ViewModel() {

    private val _waybillProduct = SingleLiveEvent<Resource<WaybillProduct>>()
    val waybillProduct: LiveData<Resource<WaybillProduct>>
        get() = _waybillProduct

    private val _product = SingleLiveEvent<Resource<ProductEntity>>()
    val product: LiveData<Resource<ProductEntity>>
        get() = _product

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _product.value = Resource.Loading
            getProductByBarcodeUseCase.invoke(barcode)
                .onFailure {
                    _product.value = Resource.Error(it)
                }.onSuccess {
                    _product.value = Resource.Success(it)
                }
        }
    }

    fun checkProductInWaybill(
        barcode: String,
        waybillId: Int
    ) {
        viewModelScope.launch {
            _waybillProduct.value = Resource.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                barcode = barcode,
                waybillId = waybillId
            ).onSuccess {
                _waybillProduct.value = Resource.Success(it)
            }.onFailure {
                findProductByBarcode(barcode)
            }
        }
    }
}