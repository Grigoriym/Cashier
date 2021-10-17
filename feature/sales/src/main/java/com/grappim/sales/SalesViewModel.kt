package com.grappim.sales

import androidx.lifecycle.*
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.products.GetProductsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class SalesViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase
) : ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
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

    init {
        getProducts()
    }

    fun addProductToBasket(product: Product) {
        viewModelScope.launch {
            addProductToBasketUseCase.invoke(AddProductToBasketUseCase.Params(product))
        }
    }

    fun removeProductFromBasket(product: Product) {
        viewModelScope.launch {
            removeProductUseCase.invoke(RemoveProductUseCase.Params(product))
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            searchProductsUseCase.invoke(SearchProductsUseCase.Params(query))
                .collect {
                    _products.value = it
                }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase.invoke(withoutParams())
                .collect {
                    _products.value = it
                }
        }
    }

}