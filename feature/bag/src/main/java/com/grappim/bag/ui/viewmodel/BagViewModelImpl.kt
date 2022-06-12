package com.grappim.bag.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.basket.ClearBasketUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.sales.AddBasketProductParams
import com.grappim.domain.interactor.sales.AddBasketProductUseCase
import com.grappim.domain.interactor.sales.RemoveProductParams
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.model.basket.BasketProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class BagViewModelImpl @Inject constructor(
    private val addBasketProduct: AddBasketProductUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val clearBasketUseCase: ClearBasketUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : BagViewModel() {

    override val basketProducts = MutableStateFlow<List<BasketProduct>>(emptyList())

    override val changedProduct = MutableStateFlow<BasketProduct?>(null)

    override val basketCount: StateFlow<String> =
        basketProducts
            .map { list ->
                list.map {
                    it.amount
                }.sumOf { it }
            }
            .map {
                dfSimple.format(it)
            }.stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = dfSimple.format(bigDecimalZero())
            )

    override val basketSum: StateFlow<String> =
        basketProducts
            .map { list ->
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

    init {
        getBagProducts()
    }

    override fun showScanner() {
        flowRouter.goToScanner()
    }

    override fun goToPaymentMethod() {
        flowRouter.goToPaymentMethod()
    }

    override fun deleteBagProducts() {
        viewModelScope.launch {
            _loading.value = true
            val result = clearBasketUseCase.clearBasket()
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    basketProducts.value = emptyList()
                    changedProduct.value = null
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    override fun getBagProducts() {
        viewModelScope.launch {
            _loading.value = true
            val result = getBagProductsUseCase.execute()
            _loading.value = false
            changedProduct.value = null
            when (result) {
                is Try.Success -> {
                    basketProducts.value = result.result
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    override fun addProductToBasket(product: BasketProduct) {
        viewModelScope.launch {
            _loading.value = true
            val result = addBasketProduct
                .execute(AddBasketProductParams(product))
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    changedProduct.value = result.result
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    override fun subtractBasketProduct(product: BasketProduct) {
        viewModelScope.launch {
            _loading.value = true
            val result = removeProductUseCase.execute(RemoveProductParams(product))
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    if (result.result == null) {
                        val products = basketProducts.value.toMutableList()
                        products.remove(product)
                        basketProducts.value = products
                    } else {
                        changedProduct.value = result.result
                    }
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }
}