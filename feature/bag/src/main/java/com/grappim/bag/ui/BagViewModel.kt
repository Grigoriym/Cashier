package com.grappim.bag.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.BaseViewModel
import com.grappim.domain.interactor.basket.DeleteBagProductsUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

class BagViewModel @Inject constructor(
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val deleteBagProductsUseCase: DeleteBagProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val bagScreenNavigator: BagScreenNavigator
) : BaseViewModel() {

    private val _products = MutableStateFlow<List<Product>>(
        emptyList()
    )
    val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()

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
    val basketSum: StateFlow<String> =
        _basketSum.map { list ->
            list.map {
                it.sellingPrice * it.basketCount
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = "0"
        )

    init {
        getBagProducts()
    }

    fun showScanner() {
        bagScreenNavigator.goToScannerFromBag()
//        navigator.navigate(
//            BagFragmentDirections.actionBagFragmentToScannerFragment(
//                ScanType.SINGLE
//            )
//        )
    }

    fun onBackPressed() {
        bagScreenNavigator.goBack()
    }

    fun goToPaymentMethod() {
        bagScreenNavigator.goToPaymentMethod()
    }

    fun deleteBagProducts() {
        viewModelScope.launch {
            deleteBagProductsUseCase.invoke(withoutParams())
            getBagProducts()
        }
    }

    fun getBagProducts() {
        viewModelScope.launch {
            getBagProductsUseCase.invoke(withoutParams())
                .collect {
                    when (it) {
                        is Try.Success -> {
                            _products.value = it.data!!
                        }
                    }
                }
        }
    }

    fun addProductToBasket(product: Product) {
        viewModelScope.launch {
            addProductToBasketUseCase.invoke(AddProductToBasketUseCase.Params(product))
            getBagProducts()
        }
    }

    fun removeProductFromBasket(product: Product) {
        viewModelScope.launch {
            removeProductUseCase.invoke(RemoveProductUseCase.Params(product))
            getBagProducts()
        }
    }
}