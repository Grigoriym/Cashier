package com.grappim.cashier.ui.bag

import androidx.lifecycle.*
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.basket.DeleteBagProductsUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.model.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val getBagProductsUseCase: GetBagProductsUseCase,
    private val deleteBagProductsUseCase: DeleteBagProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase
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
    val basketSum: LiveData<BigDecimal> =
        _basketSum.asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.sellingPrice * it.basketCount
            }.sumOf {
                it
            }
        }

    init {
        getBagProducts()
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