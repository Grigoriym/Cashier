package com.grappim.bag

import androidx.lifecycle.*
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.basket.DeleteBagProductsUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.model.product.Product
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val deleteBagProductsUseCase: DeleteBagProductsUseCase,
    private val navigator: Navigator,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

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
//        navigator.navigate(
//            BagFragmentDirections.actionBagFragmentToScannerFragment(
//                ScanType.SINGLE
//            )
//        )
    }

    fun onBackPressed() {
        navigator.popBackStack()
    }

    fun goToPaymentMethod() {
        navigator.navigateToFlow(NavigationFlow.PaymentMethod)
    }

    fun deleteBagProducts() {
        viewModelScope.launch {
            deleteBagProductsUseCase.invoke(withoutParams())
            getBagProducts()
        }
    }

    fun getBagProducts() {
        viewModelScope.launch {
            getBagProductsUseCase.invoke(NoParams())
                .collect {
                    when (it) {
                        is Result.Success -> {
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