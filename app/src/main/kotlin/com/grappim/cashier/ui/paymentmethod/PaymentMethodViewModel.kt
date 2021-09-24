package com.grappim.cashier.ui.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.domain.payment.MakePaymentUseCase
import com.grappim.cashier.domain.products.GetBagProductsUseCase
import com.grappim.cashier.domain.sales.GetAllBasketProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val paymentMethodItemGenerator: PaymentMethodItemGenerator,
    private val makePaymentUseCase: MakePaymentUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase
) : ViewModel() {

    private val _paymentStatus = MutableLiveData<Resource<Unit>>()
    val paymentStatus: LiveData<Resource<Unit>>
        get() = _paymentStatus

    private val _paymentItems = paymentMethodItemGenerator.paymentMethodItems
    val paymentItems = _paymentItems.asLiveData(viewModelScope.coroutineContext)

    private val _basketCount = getAllBasketProductsUseCase.invoke()
    val basketCount: LiveData<BigDecimal> =
        _basketCount.asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.basketCount
            }.sumOf {
                it
            }
        }
    private val _basketSum = getAllBasketProductsUseCase.invoke()
    val basketSum: LiveData<BigDecimal> =
        _basketSum.asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.sellingPrice * it.basketCount
            }.sumOf {
                it
            }
        }

    fun makePayment(paymentMethod: PaymentMethod) {
        viewModelScope.launch {
            makePaymentUseCase.invoke(paymentMethod)
                .onFailure {
                    _paymentStatus.value = Resource.Error(it)
                }
                .onSuccess {
                    _paymentStatus.value = Resource.Success(it)
                }
        }
    }
}