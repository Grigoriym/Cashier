package com.grappim.waybill.ui.product.ui.viewmodel

import androidx.compose.runtime.State
import com.grappim.common.lce.Try
import com.grappim.core.BaseViewModel
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.waybill.ui.product.model.WaybillProductStates
import java.math.BigDecimal

abstract class WaybillProductViewModel : BaseViewModel() {
    abstract val waybillProductState: State<WaybillProductStates>
    abstract val productCreated: State<Try<BigDecimal>>

    abstract fun setBarcode(barcode: String)
    abstract fun setWaybillProductName(name: String)
    abstract fun setPurchasePrice(purchasePrice: String)
    abstract fun setSellingPrice(sellingPrice: String)
    abstract fun setAmount(amount: String)
    abstract fun setWaybillProductState(
        product: Product?,
        waybillId: Int,
        waybillProduct: WaybillProduct?,
        barcode: String?
    )

    abstract fun waybillProductAction()
    abstract fun updateWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long,
        id: Long
    )

    abstract fun createWaybillProduct(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    )
}