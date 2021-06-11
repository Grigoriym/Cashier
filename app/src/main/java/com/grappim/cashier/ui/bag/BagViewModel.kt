package com.grappim.cashier.ui.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.data.db.entity.BasketProductEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.products.DeleteBagProductsUseCase
import com.grappim.cashier.domain.products.GetBagProductsUseCase
import com.grappim.cashier.domain.products.GetProductsUseCase
import com.grappim.cashier.domain.sales.AddProductToBasketUseCase
import com.grappim.cashier.domain.sales.GetAllBasketProductsUseCase
import com.grappim.cashier.domain.sales.RemoveProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>>
        get() = _products

    private val _basketCount = getAllBasketProductsUseCase.invoke()
    val basketCount: LiveData<BigDecimal> =
        _basketCount.asLiveData(viewModelScope.coroutineContext).map { list ->
            list.map {
                it.basketCount
            }.sumOf {
                it
            }
        }
    private val _basketSum = getAllBasketProductsUseCase.invoke()
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
            deleteBagProductsUseCase.invoke()
            getBagProducts()
        }
    }

    fun getBagProducts() {
        viewModelScope.launch {
            _products.value = getBagProductsUseCase.invoke()
        }
    }

    fun addProductToBasket(basketProductEntity: ProductEntity) {
        viewModelScope.launch {
            addProductToBasketUseCase.invoke(basketProductEntity)
            getBagProducts()
        }
    }

    fun removeProductFromBasket(basketProductEntity: ProductEntity) {
        viewModelScope.launch {
            removeProductUseCase.invoke(basketProductEntity)
            getBagProducts()
        }
    }
}