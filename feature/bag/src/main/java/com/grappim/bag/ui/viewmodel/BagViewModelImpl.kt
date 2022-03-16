package com.grappim.bag.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.functional.WhileViewSubscribed
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

class BagViewModelImpl @Inject constructor(
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val deleteBagProductsUseCase: DeleteBagProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : BagViewModel() {

    override val products = MutableStateFlow<List<Product>>(emptyList())

    override val basketCount: LiveData<BigDecimal> =
        getAllBasketProductsUseCase.invoke(withoutParams())
            .asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.basketCount
            }.sumOf {
                it
            }
        }

    override val basketSum: StateFlow<String> =
        getAllBasketProductsUseCase.invoke(withoutParams()).map { list ->
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

    override fun showScanner() {
        flowRouter.goToScanner()
    }

    override fun goToPaymentMethod() {
        flowRouter.goToPaymentMethod()
    }

    override fun deleteBagProducts() {
        viewModelScope.launch {
            deleteBagProductsUseCase.invoke(withoutParams())
            getBagProducts()
        }
    }

    override fun getBagProducts() {
        viewModelScope.launch {
            getBagProductsUseCase.invoke(withoutParams())
                .collect {
                    when (it) {
                        is Try.Success -> {
                            products.value = it.data!!
                        }
                    }
                }
        }
    }

    override fun addProductToBasket(product: Product) {
        viewModelScope.launch {
            addProductToBasketUseCase.invoke(AddProductToBasketUseCase.Params(product))
            getBagProducts()
        }
    }

    override fun removeProductFromBasket(product: Product) {
        viewModelScope.launch {
            removeProductUseCase.invoke(RemoveProductUseCase.Params(product))
            getBagProducts()
        }
    }
}