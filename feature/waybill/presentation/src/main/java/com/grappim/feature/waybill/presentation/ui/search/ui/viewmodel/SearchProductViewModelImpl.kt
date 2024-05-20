package com.grappim.feature.waybill.presentation.ui.search.ui.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.core.utils.BundleArgsHelper
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductBarcodeParams
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductByBarcodeUseCase
import com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsParams
import com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsUseCase
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode.GetWaybillProductByBarcodeUseCase
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchProductViewModelImpl @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getWaybillProductByBarcodeUseCase: GetWaybillProductByBarcodeUseCase,
    private val waybillLocalRepository: WaybillLocalRepository
) : SearchProductViewModel() {

    override val searchText = MutableStateFlow("")
    override val productsFlow: Flow<PagingData<Product>> = searchText.flatMapConcat {
        searchProductsUseCase.execute(
            SearchProductsParams(
                it
            )
        )
            .cachedIn(viewModelScope)
    }

    override fun setSearchText(text: String) {
        searchText.value = text
    }

    private fun findProductByBarcode(barcode: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = getProductByBarcodeUseCase.execute(
                GetProductBarcodeParams(
                    barcode
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    showProductDetails(result.result)
                }

                is Try.Error -> {
                    _error.value = result.result
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

    override fun checkProductInWaybill(product: Product) {
        viewModelScope.launch {
            _loading.value = true
            val result = getWaybillProductByBarcodeUseCase.execute(
                GetWaybillProductByBarcodeParams(
                    barcode = product.barcode,
                    waybillId = waybillLocalRepository.waybill.id
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    showProductDetails(result.result)
                }

                is Try.Error -> {
                    findProductByBarcode(product.barcode)
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
