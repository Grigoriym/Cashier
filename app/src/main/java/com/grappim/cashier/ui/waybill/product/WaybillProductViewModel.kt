package com.grappim.cashier.ui.waybill.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.domain.waybill.CreateWaybillProductUseCase
import com.grappim.cashier.domain.waybill.UpdateWaybillProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class WaybillProductViewModel @Inject constructor(
    private val createWaybillProductUseCase: CreateWaybillProductUseCase,
    private val updateWaybillProductUseCase: UpdateWaybillProductUseCase
) : ViewModel() {

    private val _productCreated = MutableLiveData<Resource<BigDecimal>>()
    val productCreated: LiveData<Resource<BigDecimal>>
        get() = _productCreated

    fun updateWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long,
        id: Long
    ) {
        viewModelScope.launch {
            _productCreated.value = Resource.Loading
            updateWaybillProductUseCase.invoke(
                waybillId = waybillId,
                barcode = barcode,
                name = name,
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                amount = amount,
                productId = productId,
                id = id
            ).onSuccess {
                _productCreated.value = Resource.Success(it)
            }.onFailure {
                _productCreated.value = Resource.Error(it)
            }
        }
    }

    fun createWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    ) {
        viewModelScope.launch {
            _productCreated.value = Resource.Loading
            createWaybillProductUseCase.invoke(
                waybillId = waybillId,
                barcode = barcode,
                name = name,
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                amount = amount,
                productId = productId
            ).onFailure {
                _productCreated.value = Resource.Error(it)
            }.onSuccess {
                _productCreated.value = Resource.Success(it)
            }
        }
    }
}