package com.grappim.cashier.core.storage

import android.content.Context
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.cashier.Cashier
import com.grappim.cashier.domain.outlet.Stock
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val STORAGE_NAME = "cashier_data_store"

        private const val CASHIER_NAME = "cashier_name"
        private const val CASHIER_ID = "cashier_id"

        private const val STOCK_NAME = "stock_name"
        private const val STOCK_ID = "stock_id"

        private const val MERCHANT_ID = "merchant_id"
        private const val MERCHANT_NAME = "merchant_name"

        private const val AUTH_TOKEN = "auth_token"
    }

    private val sharedPreferences = context
        .getSharedPreferences(
            STORAGE_NAME,
            Context.MODE_PRIVATE
        )

    private val editor = sharedPreferences.edit()

    fun setCashierInfo(cashBox: CashBox) {
        editor
            .putString(CASHIER_ID, cashBox.cashBoxId)
            .putString(CASHIER_NAME, cashBox.name)
            .apply()
    }

    fun setStockInfo(stock: Stock) {
        editor
            .putString(STOCK_NAME, stock.name)
            .putString(STOCK_ID, stock.stockId)
            .apply()
    }

    fun setAuthToken(token: String) {
        editor
            .putString(AUTH_TOKEN, token)
            .apply()
    }

    fun setMerchantInfo(merchantId: String, merchantName: String) {
        editor
            .putString(MERCHANT_ID, merchantId)
            .putString(MERCHANT_NAME, merchantName)
            .apply()
    }

    fun getCashierName(): String = getStringValue(CASHIER_NAME)

    fun getCashierId(): String = getStringValue(CASHIER_ID)

    fun getOutletName(): String = getStringValue(STOCK_NAME)

    fun getMerchantId(): String = getStringValue(MERCHANT_ID)

    fun getMerchantName(): String = getStringValue(MERCHANT_NAME)

    fun getStockId(): String = getStringValue(STOCK_ID)

    fun getToken(): String = getStringValue(AUTH_TOKEN)

    fun getBearerAuthToken(): String =
        "Bearer ${getStringValue(AUTH_TOKEN)}"

    fun clearData() {
        editor.clear().apply()
    }

    private fun getStringValue(key: String): String =
        sharedPreferences.getString(key, null)
            ?: throw IllegalArgumentException("no value for $key")

}