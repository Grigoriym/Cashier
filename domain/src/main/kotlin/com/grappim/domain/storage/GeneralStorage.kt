package com.grappim.domain.storage

import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock

interface GeneralStorage {

    fun setCashierInfo(cashBox: CashBox)
    fun setStockInfo(stock: Stock)
    fun setAuthToken(token: String)
    fun setMerchantInfo(merchantId: String, merchantName: String)

    fun getCashierName(): String

    fun getCashierId(): String

    fun getOutletName(): String

    fun getMerchantId(): String

    fun getMerchantName(): String

    fun getStockId(): String

    fun getToken(): String

    fun getBearerAuthToken(): String

    fun clearData()

}