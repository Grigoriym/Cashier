package com.grappim.waybill.ui.product.ui.viewmodel

import androidx.compose.runtime.State
import com.grappim.core.base.BaseViewModel
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.waybill.ui.product.model.WaybillProductStates
import java.math.BigDecimal

abstract class WaybillProductViewModel : BaseViewModel() {
    abstract val waybillProductState: State<WaybillProductStates>

    abstract fun setBarcode(barcode: String)
    abstract fun setWaybillProductName(name: String)
    abstract fun setPurchasePrice(purchasePrice: String)
    abstract fun setSellingPrice(sellingPrice: String)
    abstract fun setAmount(amount: String)
    abstract fun setWaybillProductState(
        product: Product?,
        waybillProduct: WaybillProduct?,
        barcode: String?
    )

    abstract fun waybillProductAction()
    abstract fun updateWaybillProduct(
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long,
        id: Long
    )

    abstract fun createWaybillProduct(
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    )
}