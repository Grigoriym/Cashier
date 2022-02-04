package com.grappim.bag.ui.viewmodel

import androidx.lifecycle.LiveData
import com.grappim.core.BaseViewModel
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

abstract class BagViewModel : BaseViewModel() {

    abstract val products: StateFlow<List<Product>>
    abstract val basketCount: LiveData<BigDecimal>
    abstract val basketSum: StateFlow<String>

    abstract fun showScanner()
    abstract fun onBackPressed()
    abstract fun goToPaymentMethod()
    abstract fun deleteBagProducts()
    abstract fun getBagProducts()
    abstract fun addProductToBasket(product: Product)
    abstract fun removeProductFromBasket(product: Product)

}