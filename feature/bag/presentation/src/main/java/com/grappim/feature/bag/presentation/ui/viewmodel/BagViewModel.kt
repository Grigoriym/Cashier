package com.grappim.feature.bag.presentation.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.domain.model.BasketProduct
import kotlinx.coroutines.flow.StateFlow

abstract class BagViewModel : BaseViewModel() {

    abstract val basketProducts: StateFlow<List<BasketProduct>>
    abstract val basketCount: StateFlow<String>
    abstract val basketSum: StateFlow<String>
    abstract val changedProduct: StateFlow<BasketProduct?>

    abstract fun showScanner()
    abstract fun goToPaymentMethod()
    abstract fun deleteBagProducts()
    abstract fun getBagProducts()
    abstract fun addProductToBasket(product: BasketProduct)
    abstract fun subtractBasketProduct(product: BasketProduct)

}