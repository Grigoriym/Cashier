package com.grappim.bag.ui.viewmodel

import com.grappim.core.base.BaseViewModel2
import com.grappim.domain.model.basket.BasketProduct
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

abstract class BagViewModel : BaseViewModel2() {

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