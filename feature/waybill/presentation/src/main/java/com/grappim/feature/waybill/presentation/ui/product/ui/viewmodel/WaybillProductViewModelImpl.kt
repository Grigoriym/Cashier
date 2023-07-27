package com.grappim.feature.waybill.presentation.ui.product.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalOne
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.lce.Try
import com.grappim.domain.model.Product
import com.grappim.feature.waybill.domain.interactor.createWaybillProduct.CreateWaybillProductParams
import com.grappim.feature.waybill.domain.interactor.createWaybillProduct.CreateWaybillProductUseCase
import com.grappim.feature.waybill.domain.interactor.updateWaybillProduct.UpdateWaybillProductParams
import com.grappim.feature.waybill.domain.interactor.updateWaybillProduct.UpdateWaybillProductUseCase
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.presentation.ui.product.model.WaybillProductStates
import com.grappim.feature.waybill.presentation.ui.product.model.WaybillProductType
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

class WaybillProductViewModelImpl @Inject constructor(
    private val createWaybillProductUseCase: CreateWaybillProductUseCase,
    private val updateWaybillProductUseCase: UpdateWaybillProductUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val waybillLocalRepository: WaybillLocalRepository
) : WaybillProductViewModel() {

    override val waybillProductState = mutableStateOf<WaybillProductStates>(
        WaybillProductStates.EmptyState
    )

    override fun setBarcode(barcode: String) {
        checkIfStateValid {
            waybillProductState.value = it.copy(
                barcode = barcode
            )
        }
    }

    override fun setWaybillProductName(name: String) {
        checkIfStateValid {
            waybillProductState.value = it.copy(
                name = name
            )
        }
    }

    override fun setPurchasePrice(purchasePrice: String) {
        checkIfStateValid {
            waybillProductState.value = it.copy(
                purchasePrice = dfSimple.format(purchasePrice.asBigDecimal()).asBigDecimal(),
                purchasePriceToShow = dfSimple.format(purchasePrice.asBigDecimal())
            )
        }
    }

    override fun setSellingPrice(sellingPrice: String) {
        checkIfStateValid {
            waybillProductState.value = it.copy(
                sellingPrice = dfSimple.format(sellingPrice.asBigDecimal()).asBigDecimal(),
                sellingPriceToShow = dfSimple.format(sellingPrice.asBigDecimal())
            )
        }
    }

    override fun setAmount(amount: String) {
        checkIfStateValid {
            waybillProductState.value = it.copy(
                amount = dfSimple.format(amount.asBigDecimal()).asBigDecimal(),
                amountToShow = dfSimple.format(amount.asBigDecimal())
            )
        }
    }

    private fun checkIfStateValid(
        onValid: (WaybillProductStates.WaybillProductState) -> Unit
    ) {
        val state = waybillProductState.value
        if (state is WaybillProductStates.WaybillProductState) {
            onValid(state)
        }
    }

    override fun setWaybillProductState(
        product: Product?,
        waybillProduct: WaybillProduct?,
        barcode: String?
    ) {
        if (waybillProductState.value is WaybillProductStates.EmptyState) {
            val state: WaybillProductType
            val barcodeForState: String
            val name: String
            val purchasePrice: BigDecimal
            val sellingPrice: BigDecimal
            val amount: BigDecimal
            val productId: Long
            val waybillProductId: Long?
            when {
                product != null -> {
                    state = WaybillProductType.FROM_PRODUCT
                    barcodeForState = product.barcode
                    name = product.name
                    purchasePrice = product.purchasePrice
                    sellingPrice = product.sellingPrice
                    amount = bigDecimalOne()
                    productId = product.id
                    waybillProductId = null
                }
                waybillProduct != null -> {
                    state = WaybillProductType.FROM_WAYBILL_PRODUCT
                    barcodeForState = waybillProduct.barcode
                    name = waybillProduct.name
                    purchasePrice = waybillProduct.purchasePrice
                    sellingPrice = waybillProduct.sellingPrice
                    amount = waybillProduct.amount
                    productId = waybillProduct.productId
                    waybillProductId = waybillProduct.id
                }
                else -> {
                    state = WaybillProductType.NEW_PRODUCT
                    barcodeForState = barcode!!
                    name = "New Product"
                    purchasePrice = bigDecimalZero()
                    sellingPrice = bigDecimalZero()
                    amount = bigDecimalOne()
                    productId = 0L
                    waybillProductId = null
                }
            }
            waybillProductState.value = WaybillProductStates.WaybillProductState(
                type = state,
                barcode = barcodeForState,
                name = name,
                waybillId = waybillLocalRepository.waybill.id,
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                amount = amount,
                productId = productId,
                waybillProductId = waybillProductId,
                purchasePriceToShow = dfSimple.format(purchasePrice),
                sellingPriceToShow = dfSimple.format(sellingPrice),
                amountToShow = dfSimple.format(amount)
            )
        }
    }

    override fun waybillProductAction() {
        viewModelScope.launch {
            val state = waybillProductState.value
            if (state is WaybillProductStates.WaybillProductState) {
                if (state.type == WaybillProductType.FROM_WAYBILL_PRODUCT) {
                    updateWaybillProduct(
                        barcode = state.barcode,
                        name = state.name,
                        purchasePrice = state.purchasePrice,
                        sellingPrice = state.sellingPrice,
                        amount = state.amount,
                        productId = state.productId,
                        id = state.waybillProductId!!
                    )
                } else {
                    createWaybillProduct(
                        barcode = state.barcode,
                        name = state.name,
                        purchasePrice = state.purchasePrice,
                        sellingPrice = state.sellingPrice,
                        amount = state.amount,
                        productId = state.productId,
                    )
                }
            }
        }
    }

    override fun updateWaybillProduct(
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long,
        id: Long
    ) {
        viewModelScope.launch {
            _loading.value = true
            val result = updateWaybillProductUseCase.execute(
                UpdateWaybillProductParams(
                    waybillId = waybillLocalRepository.waybill.id,
                    barcode = barcode,
                    name = name,
                    purchasePrice = purchasePrice,
                    sellingPrice = sellingPrice,
                    amount = amount,
                    productId = productId,
                    id = id
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    onProductDone()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    private fun onProductDone() {
        flowRouter.returnToWaybillFromProduct()
    }

    override fun createWaybillProduct(
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    ) {
        viewModelScope.launch {
            _loading.value = true
            val result = createWaybillProductUseCase.execute(
                CreateWaybillProductParams(
                    waybillId = waybillLocalRepository.waybill.id,
                    barcode = barcode,
                    name = name,
                    purchasePrice = purchasePrice,
                    sellingPrice = sellingPrice,
                    amount = amount,
                    productId = productId
                )
            )
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    onProductDone()
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }
}
