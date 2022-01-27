package com.grappim.waybill.ui.product.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.asBigDecimal
import com.grappim.calculations.bigDecimalOne
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.di.ApplicationContext
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.waybill.CreateWaybillProductUseCase
import com.grappim.domain.interactor.waybill.UpdateWaybillProductUseCase
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.waybill.R
import com.grappim.waybill.ui.product.model.WaybillProductStates
import com.grappim.waybill.ui.product.model.WaybillProductType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

class WaybillProductViewModelImpl @Inject constructor(
    private val createWaybillProductUseCase: CreateWaybillProductUseCase,
    private val updateWaybillProductUseCase: UpdateWaybillProductUseCase,
    @ApplicationContext private val context: Context,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : WaybillProductViewModel() {

    override val waybillProductState = mutableStateOf<WaybillProductStates>(
        WaybillProductStates.EmptyState
    )

    override val productCreated = mutableStateOf<Try<BigDecimal>>(
        Try.Initial
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
        waybillId: Int,
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
                    name = context.getString(R.string.title_new_product)
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
                waybillId = waybillId,
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
                        waybillId = state.waybillId,
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
                        waybillId = state.waybillId,
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
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long,
        id: Long
    ) {
        viewModelScope.launch {
            productCreated.value = Try.Loading
            updateWaybillProductUseCase.invoke(
                UpdateWaybillProductUseCase.Params(
                    waybillId = waybillId,
                    barcode = barcode,
                    name = name,
                    purchasePrice = purchasePrice,
                    sellingPrice = sellingPrice,
                    amount = amount,
                    productId = productId,
                    id = id
                )
            ).collect {
                productCreated.value = it
                when (it) {
                    is Try.Success -> {
//                        navigator.navigate(
//                            R.id.action_waybillProduct_to_waybillDetails,
//                            bundleOf(
//                                WaybillDetailsFragment.ARG_TOTAL_COST to it.data
//                            )
//                        )
                    }
                }
            }
        }
    }

    override fun createWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    ) {
        viewModelScope.launch {
            productCreated.value = Try.Loading
            createWaybillProductUseCase.invoke(
                CreateWaybillProductUseCase.Params(
                    waybillId = waybillId,
                    barcode = barcode,
                    name = name,
                    purchasePrice = purchasePrice,
                    sellingPrice = sellingPrice,
                    amount = amount,
                    productId = productId
                )
            ).collect {
                productCreated.value = it
            }
        }
    }
}