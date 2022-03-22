package com.grappim.repository.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.di.AppScope
import com.grappim.common.di.ApplicationContext
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.storage.GeneralStorage
import com.grappim.repository.utils.string
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class GeneralStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GeneralStorage {

    companion object {
        private const val STORAGE_NAME = "cashier_data_store"
        private const val FLOW_STORAGE_NAME = "cashier_flow_data_store"

        private const val CASH_BOX_NAME = "cash_box_name"
        private const val CASH_BOX_ID = "cash_box_id"

        private const val STOCK_NAME = "stock_name"
        private const val STOCK_ID = "stock_id"

        private const val MERCHANT_ID = "merchant_id"
        private const val MERCHANT_NAME = "merchant_name"

        private const val AUTH_TOKEN = "auth_token"

        private const val AUTH_ERROR = "auth_error"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FLOW_STORAGE_NAME)

    private val sharedPreferences = context
        .getSharedPreferences(
            STORAGE_NAME,
            Context.MODE_PRIVATE
        )

    private val editor = sharedPreferences.edit()

    private val AUTHENTICATION_ERROR = booleanPreferencesKey(AUTH_ERROR)
    override val authErrorFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[AUTHENTICATION_ERROR] ?: false
        }

    override var cashBoxName: String by sharedPreferences.string(CASH_BOX_NAME)

    override var stockName: String by sharedPreferences.string(STOCK_NAME)

    override var cashBoxId: String by sharedPreferences.string(CASH_BOX_ID)

    override var stockId: String by sharedPreferences.string(STOCK_ID)

    override suspend fun setAuthErrorFlow(isError: Boolean) {
        context.dataStore.edit { settings ->
            settings[AUTHENTICATION_ERROR] = isError
        }
    }

    override fun setCashBoxInfo(cashBox: CashBox) {
        cashBoxName = cashBox.name
        cashBoxId = cashBox.cashBoxId
    }

    override fun setStockInfo(stock: Stock) {
        stockName = stock.name
        stockId = stock.stockId
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

    override fun getMerchantId(): String = getStringValue(MERCHANT_ID)

    override fun getMerchantIdNullable(): String? = sharedPreferences.getString(MERCHANT_ID, null)

    override fun getMerchantName(): String = getStringValue(MERCHANT_NAME)

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