package com.grappim.cashier.ui.paymentmethod

import androidx.lifecycle.*
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    paymentMethodItemGenerator: PaymentMethodItemGenerator,
    private val makePaymentUseCase: MakePaymentUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase
) : ViewModel() {

    private val _paymentStatus = MutableLiveData<Result<Unit>>()
    val paymentStatus: LiveData<Result<Unit>>
        get() = _paymentStatus

    private val _paymentItems = paymentMethodItemGenerator.paymentMethodItems
    val paymentItems = _paymentItems.asLiveData(viewModelScope.coroutineContext)

    private val _basketCount = getAllBasketProductsUseCase.invoke(withoutParams())
    val basketCount: LiveData<BigDecimal> =
        _basketCount.asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.basketCount
            }.sumOf {
                it
            }
        }
    private val _basketSum = getAllBasketProductsUseCase.invoke(withoutParams())
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
            makePaymentUseCase.invoke(MakePaymentUseCase.Params(paymentMethod.type))
                .collect {
                    _paymentStatus.value = it
                }
        }
    }
}