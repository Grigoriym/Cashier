package com.grappim.paymentmethod.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.feature.bag.domain.interactor.GetBasketItemsUseCase
import com.grappim.feature.paymentmethod.domain.interactor.MakePaymentParams
import com.grappim.feature.paymentmethod.domain.interactor.MakePaymentUseCase
import com.grappim.logger.logE
import com.grappim.paymentmethod.helper.PaymentMethodItemGenerator
import com.grappim.paymentmethod.model.PaymentMethod
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class PaymentMethodViewModelImpl @Inject constructor(
    paymentMethodItemGenerator: PaymentMethodItemGenerator,
    getBasketItemsUseCase: GetBasketItemsUseCase,
    private val makePaymentUseCase: MakePaymentUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : PaymentMethodViewModel() {

    override val paymentItems = paymentMethodItemGenerator.paymentMethodItems

    override val basketCount: StateFlow<String> = getBasketItemsUseCase
        .execute().map { list ->
            list.map {
                it.sellingPrice
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.catch { e ->
            logE(e)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = dfSimple.format(bigDecimalZero())
        )

    override val basketSum: StateFlow<String> = getBasketItemsUseCase
        .execute().map { list ->
            list.map {
                it.sellingPrice * it.amount
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = dfSimple.format(bigDecimalZero())
        )

    override fun makePayment(paymentMethod: PaymentMethod) {
        viewModelScope.launch {
            _loading.value = true
            val result = makePaymentUseCase.execute(MakePaymentParams(paymentMethod.type))
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    flowRouter.returnToSalesFromPaymentMethod()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }
}
