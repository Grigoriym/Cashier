package com.grappim.waybill.ui.search.ui

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.BaseViewModel
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchProductViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val waybillLocalRepository: WaybillLocalRepository,
    private val waybillScreenNavigator: WaybillScreenNavigator
) : BaseViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText.asStateFlow()

    val productsFlow = searchText.flatMapConcat {
        searchProductsUseCase.invoke(SearchProductsUseCase.Params(it))
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = emptyList()
    )

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun onBackPressed() {
        waybillScreenNavigator.goBack()
    }

    private val _waybillProduct = MutableStateFlow<Try<WaybillProduct>>(
        Try.Initial
    )
    val waybillProduct: StateFlow<Try<WaybillProduct>>
        get() = _waybillProduct.asStateFlow()

    private val _product = MutableStateFlow<Try<Product>>(
        Try.Initial
    )
    val product: StateFlow<Try<Product>>
        get() = _product.asStateFlow()

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _product.value = Try.Loading
            getProductByBarcodeUseCase.invoke(GetProductByBarcodeUseCase.Params(barcode))
                .collect {
                    _product.value = it
                    when (it) {
                        is Try.Success -> {
                            showProductDetails(it.data)
                        }
                    }
                }
        }
    }

    private fun showProductDetails(product: Product) {
//        navigator.navigate(
//            R.id.action_search_to_waybillProduct,
//            bundleOf(
//                WaybillProductFragment.ARG_WAYBILL_ID to waybillLocalRepository.waybill.id,
//                WaybillProductFragment.ARG_PRODUCT to product
//            )
//        )
    }

    fun checkProductInWaybill(
        product: Product
    ) {
        viewModelScope.launch {
            _waybillProduct.value = Try.Loading
            getWaybillProductByBarcodeUseCase.invoke(
                GetWaybillProductByBarcodeUseCase.Params(
                    barcode = product.barcode,
                    waybillId = waybillLocalRepository.waybill.id
                )
            ).collect {
                when (it) {
                    is Try.Success -> {
                        showProductDetails(it.data)
                    }
                    is Try.Error -> {
                        findProductByBarcode(product.barcode)
                    }
                }
            }
        }
    }

    private fun showProductDetails(waybillProduct: WaybillProduct) {
//        navigator.navigate(
//            R.id.action_search_to_waybillProduct,
//            bundleOf(
//                WaybillProductFragment.ARG_WAYBILL_ID to waybillLocalRepository.waybill.id,
//                WaybillProductFragment.ARG_WAYBILL_PRODUCT to waybillProduct
//            )
//        )
    }

}