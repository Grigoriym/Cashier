package com.grappim.repository.storage

import android.content.Context
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.storage.GeneralStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GeneralStorage {

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

    override fun setCashierInfo(cashBox: CashBox) {
        editor
            .putString(CASHIER_ID, cashBox.cashBoxId)
            .putString(CASHIER_NAME, cashBox.name)
            .apply()
    }

    override fun setStockInfo(stock: Stock) {
        editor
            .putString(STOCK_NAME, stock.name)
            .putString(STOCK_ID, stock.stockId)
            .apply()
    }

    override fun setAuthToken(token: String) {
        editor
            .putString(AUTH_TOKEN, token)
            .apply()
    }

    override fun setMerchantInfo(merchantId: String, merchantName: String) {
        editor
            .putString(MERCHANT_ID, merchantId)
            .putString(MERCHANT_NAME, merchantName)
            .apply()
    }

    override fun getCashierName(): String = getStringValue(CASHIER_NAME)

    override fun getCashierId(): String = getStringValue(CASHIER_ID)

    override fun getOutletName(): String = getStringValue(STOCK_NAME)

    override fun getMerchantId(): String = getStringValue(MERCHANT_ID)

    override fun getMerchantName(): String = getStringValue(MERCHANT_NAME)

    override fun getStockId(): String = getStringValue(STOCK_ID)

    override fun getToken(): String = getStringValue(AUTH_TOKEN)

    override fun getBearerAuthToken(): String =
        "Bearer ${getStringValue(AUTH_TOKEN)}"

    override fun clearData() {
        editor.clear().apply()
    }

    private fun getStringValue(key: String): String =
        sharedPreferences.getString(key, null)
            ?: throw IllegalArgumentException("no value for $key")

}