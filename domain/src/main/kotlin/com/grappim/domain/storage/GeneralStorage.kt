package com.grappim.domain.storage

import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock

interface GeneralStorage {

    val cashBoxName: String
    val stockName: String
    val cashBoxId: String
    val stockId: String

    fun setCashBoxInfo(cashBox: CashBox)
    fun setStockInfo(stock: Stock)
    fun setAuthToken(token: String)
    fun setMerchantInfo(merchantId: String, merchantName: String)

    fun getMerchantId(): String
    fun getMerchantIdNullable(): String?

    fun getMerchantName(): String

    fun getToken(): String

    fun getBearerAuthToken(): String

    fun clearData()

}