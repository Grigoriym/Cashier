package com.grappim.cashier.ui.waybill.product

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.asBigDecimal
import com.grappim.cashier.core.extensions.bigDecimalOne
import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.domain.waybill.CreateWaybillProductUseCase
import com.grappim.cashier.domain.waybill.UpdateWaybillProductUseCase
import com.grappim.cashier.domain.waybill.WaybillProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class WaybillProductViewModel @Inject constructor(
    private val createWaybillProductUseCase: CreateWaybillProductUseCase,
    private val updateWaybillProductUseCase: UpdateWaybillProductUseCase,
    @ApplicationContext private val context: Context,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : ViewModel() {

    private val _waybillProductState = mutableStateOf<WaybillProductStates>(
        WaybillProductStates.EmptyState
    )
    val waybillProductState: State<WaybillProductStates>
        get() = _waybillProductState

    private val _productCreated = mutableStateOf<Resource<BigDecimal>>(
        Resource.Initial
    )
    val productCreated: State<Resource<BigDecimal>>
        get() = _productCreated

    fun setBarcode(barcode: String) {
        checkIfStateValid {
            _waybillProductState.value = it.copy(
                barcode = barcode
            )
        }
    }

    fun setWaybillProductName(name: String) {
        checkIfStateValid {
            _waybillProductState.value = it.copy(
                name = name
            )
        }
    }

    fun setPurchasePrice(purchasePrice: String) {
        checkIfStateValid {
            _waybillProductState.value = it.copy(
                purchasePrice = dfSimple.format(purchasePrice.asBigDecimal()).asBigDecimal(),
                purchasePriceToShow = dfSimple.format(purchasePrice.asBigDecimal())
            )
        }
    }

    fun setSellingPrice(sellingPrice: String) {
        checkIfStateValid {
            _waybillProductState.value = it.copy(
                sellingPrice = dfSimple.format(sellingPrice.asBigDecimal()).asBigDecimal(),
                sellingPriceToShow = dfSimple.format(sellingPrice.asBigDecimal())
            )
        }
    }

    fun setAmount(amount: String) {
        checkIfStateValid {
            _waybillProductState.value = it.copy(
                amount = dfSimple.format(amount.asBigDecimal()).asBigDecimal(),
                amountToShow = dfSimple.format(amount.asBigDecimal())
            )
        }
    }

    private fun checkIfStateValid(
        onValid: (WaybillProductStates.WaybillProductState) -> Unit
    ) {
        val state = _waybillProductState.value
        if (state is WaybillProductStates.WaybillProductState) {
            onValid(state)
        }
    }

    fun setWaybillProductState(
        productEntity: ProductEntity?,
        waybillId: Int,
        waybillProduct: WaybillProduct?,
        barcode: String?
    ) {
        if (_waybillProductState.value is WaybillProductStates.EmptyState) {
            val state: WaybillProductType
            val barcodeForState: String
            val name: String
            val purchasePrice: BigDecimal
            val sellingPrice: BigDecimal
            val amount: BigDecimal
            val productId: Long
            val waybillProductId: Long?
            when {
                productEntity != null -> {
                    state = WaybillProductType.FROM_PRODUCT
                    barcodeForState = productEntity.barcode
                    name = productEntity.name
                    purchasePrice = productEntity.purchasePrice
                    sellingPrice = productEntity.sellingPrice
                    amount = bigDecimalOne()
                    productId = productEntity.id
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
            _waybillProductState.value = WaybillProductStates.WaybillProductState(
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

    fun waybillProductAction() {
        viewModelScope.launch {
            val state = _waybillProductState.value
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

    fun updateWaybillProduct(
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
            _productCreated.value = Resource.Loading
            updateWaybillProductUseCase.invoke(
                waybillId = waybillId,
                barcode = barcode,
                name = name,
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                amount = amount,
                productId = productId,
                id = id
            ).onSuccess {
                _productCreated.value = Resource.Success(it)
            }.onFailure {
                _productCreated.value = Resource.Error(it)
            }
        }
    }

    fun createWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    ) {
        viewModelScope.launch {
            _productCreated.value = Resource.Loading
            createWaybillProductUseCase.invoke(
                waybillId = waybillId,
                barcode = barcode,
                name = name,
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                amount = amount,
                productId = productId
            ).onFailure {
                _productCreated.value = Resource.Error(it)
            }.onSuccess {
                _productCreated.value = Resource.Success(it)
            }
        }
    }
}

sealed class WaybillProductStates {
    data class WaybillProductState(
        val type: WaybillProductType,
        val waybillId: Int,
        val productId: Long,
        val waybillProductId: Long?,

        val barcode: String,
        val name: String,

        val purchasePrice: BigDecimal,
        val purchasePriceToShow:String,
        val sellingPrice: BigDecimal,
        val sellingPriceToShow:String,
        val amount: BigDecimal,
        val amountToShow:String
    ) : WaybillProductStates()

    object EmptyState : WaybillProductStates()
}

enum class WaybillProductType {
    NEW_PRODUCT,
    FROM_PRODUCT,
    FROM_WAYBILL_PRODUCT
}