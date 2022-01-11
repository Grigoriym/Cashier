package com.grappim.sales.ui

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.common.lce.withoutParams
import com.grappim.core.BaseViewModel
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.product.Product
import com.grappim.sales.di.SalesScreenNavigator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class SalesViewModel @Inject constructor(
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val salesScreenNavigator: SalesScreenNavigator
) : BaseViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String>
        get() = _searchQuery.asStateFlow()

    val products: StateFlow<List<Product>> = _searchQuery
        .flatMapLatest {
            searchProductsUseCase.invoke(SearchProductsUseCase.Params(it))
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = emptyList()
        )

    fun setQuery(query: String) {
        _searchQuery.value = query
    }

    private val _basketCount = getAllBasketProductsUseCase.invoke(withoutParams())
    val basketCount: StateFlow<String> =
        _basketCount
            .map { list ->
                list.map {
                    it.basketCount
                }.sumOf {
                    it
                }
            }.map {
                dfSimple.format(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = "0"
            )

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addProductToBasketUseCase.invoke(AddProductToBasketUseCase.Params(product))
        }
    }

    fun onBackPressed() {
        salesScreenNavigator.goBack()
    }

    fun subtractProduct(product: Product) {
        viewModelScope.launch {
            removeProductUseCase.invoke(RemoveProductUseCase.Params(product))
        }
    }

    fun onCartClicked(product: Product) {
        addProduct(product)
    }

    fun showScanner() {
//        findNavController().navigate(SalesFragmentDirections.actionSalesFragmentToScannerFragment())
    }

    fun showBasket() {
        salesScreenNavigator.goToBag()
    }

}