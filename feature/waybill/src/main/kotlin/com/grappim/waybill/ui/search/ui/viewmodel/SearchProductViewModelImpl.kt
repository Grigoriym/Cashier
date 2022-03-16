package com.grappim.waybill.ui.search.ui.viewmodel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchProductViewModelImpl @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val waybillLocalRepository: WaybillLocalRepository
) : SearchProductViewModel() {

    override val searchText = MutableStateFlow("")
    override val productsFlow: StateFlow<List<Product>> = searchText.flatMapConcat {
        searchProductsUseCase.invoke(SearchProductsUseCase.Params(it))
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = emptyList()
    )

    override val waybillProduct = MutableStateFlow<Try<WaybillProduct>>(Try.Initial)

    override fun setSearchText(text: String) {
        searchText.value = text
    }

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            getProductByBarcodeUseCase.invoke(GetProductByBarcodeUseCase.Params(barcode))
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            showProductDetails(it.data)
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    private fun showProductDetails(product: Product) {
        val args = bundleOf(
            BundleArgsHelper.Waybill.ARG_KEY_PRODUCT to product
        )
        flowRouter.goToWaybillProduct(args)
    }

    override fun checkProductInWaybill(
        product: Product
    ) {
        viewModelScope.launch {
            waybillProduct.value = Try.Loading
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
        val args = bundleOf(
            BundleArgsHelper.Waybill.ARG_KEY_WAYBILL_PRODUCT to waybillProduct
        )
        flowRouter.goToWaybillProduct(args)
    }

}