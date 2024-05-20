package com.grappim.sales.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.bigDecimalZero
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.model.Product
import com.grappim.feature.bag.domain.interactor.GetBasketItemsUseCase
import com.grappim.feature.bag.domain.interactor.addProductToBasket.AddProductToBasketParams
import com.grappim.feature.bag.domain.interactor.addProductToBasket.AddProductToBasketUseCase
import com.grappim.feature.bag.domain.interactor.subtractProductFromBasket.SubtractProductFromBasketParams
import com.grappim.feature.bag.domain.interactor.subtractProductFromBasket.SubtractProductFromBasketUseCase
import com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsParams
import com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class SalesViewModel @Inject constructor(
    getBasketItemsUseCase: GetBasketItemsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val subtractProductFromBasketUseCase: SubtractProductFromBasketUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : BaseViewModel() {

    private val _productChanged = MutableStateFlow(false)
    val productChanged: StateFlow<Boolean>
        get() = _productChanged.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String>
        get() = _searchQuery.asStateFlow()

    val products: Flow<PagingData<Product>> = searchQuery.flatMapLatest { query ->
        searchProductsUseCase.execute(
            SearchProductsParams(query)
        ).cachedIn(viewModelScope)
    }

    val basketCount: StateFlow<String> = getBasketItemsUseCase
        .execute()
        .map { list ->
            list.map {
                it.sellingPrice
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = dfSimple.format(bigDecimalZero())
        )

    fun setProductChanged(changed: Boolean) {
        viewModelScope.launch {
            _productChanged.value = changed
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            _searchQuery.value = query
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            _loading.value = true
            val result = addProductToBasketUseCase.execute(
                AddProductToBasketParams(product)
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    _productChanged.value = true
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    fun subtractProduct(product: Product) {
        viewModelScope.launch {
            _loading.value = true
            val result = subtractProductFromBasketUseCase.execute(
                SubtractProductFromBasketParams(
                    product
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    _productChanged.value = true
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    fun onCartClicked(product: Product) {
        addProduct(product)
    }

    fun showScanner() {
        flowRouter.goToScanner()
    }

    fun showBasket() {
        flowRouter.goToBag()
    }
}
