package com.grappim.waybill.ui.search.ui.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.common.lce.Try
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.domain.interactor.products.GetProductBarcodeParams
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.sales.SearchProductsParams
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeParams
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
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
        searchProductsUseCase.execute(SearchProductsParams(it))
            .cachedIn(viewModelScope)
    }

    override fun setSearchText(text: String) {
        searchText.value = text
    }

    private fun findProductByBarcode(
        barcode: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val result = getProductByBarcodeUseCase.execute(GetProductBarcodeParams(barcode))
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

    override fun checkProductInWaybill(
        product: Product
    ) {
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