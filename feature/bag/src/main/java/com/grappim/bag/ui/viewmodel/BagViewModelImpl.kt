package com.grappim.bag.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.basket.ClearBasketUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.sales.AddBasketProduct
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
    private val addBasketProduct: AddBasketProduct,
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
            clearBasketUseCase.invoke(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            basketProducts.value = emptyList()
                            changedProduct.value = null
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    override fun getBagProducts() {
        viewModelScope.launch {
            getBagProductsUseCase.invoke(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading
                    changedProduct.value = null
                    when (it) {
                        is Try.Success -> {
                            basketProducts.value = it.data
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    override fun addProductToBasket(product: BasketProduct) {
        viewModelScope.launch {
            addBasketProduct
                .invoke(AddBasketProduct.Params(product))
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            changedProduct.value = it.data
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    override fun subtractBasketProduct(product: BasketProduct) {
        viewModelScope.launch {
            removeProductUseCase.invoke(RemoveProductUseCase.Params(product))
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            if (it.data == null) {
                                val products = basketProducts.value.toMutableList()
                                products.remove(product)
                                basketProducts.value = products
                            } else {
                                changedProduct.value = it.data
                            }
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }
}