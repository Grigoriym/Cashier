package com.grappim.sales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.products.GetProductsUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.product.Product
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
internal class SalesViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductUseCase: RemoveProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val navigator: Navigator
) : ViewModel() {

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
        navigator.popBackStack()
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
        navigator.navigateToFlow(NavigationFlow.BagFlow)
    }

}