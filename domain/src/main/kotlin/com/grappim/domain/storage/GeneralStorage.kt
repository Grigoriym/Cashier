package com.grappim.domain.storage

import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.outlet.Stock
import kotlinx.coroutines.flow.Flow

interface GeneralStorage {

    val authErrorFlow: Flow<Boolean>

    val cashBoxName: String
    val stockName: String
    val cashBoxId: String
    val stockId: String

    val biometricsStatus: Flow<BiometricsStatus>

    suspend fun setAuthErrorFlow(isError: Boolean)

    fun setCashBoxInfo(cashBox: CashBox)
    fun setStockInfo(stock: Stock)
    fun setAuthToken(token: String)
    fun setMerchantInfo(merchantId: String, merchantName: String)

    fun getMerchantId(): String
    fun getMerchantIdNullable(): String?

    fun getMerchantName(): String

    fun getToken(): String

    fun getBearerAuthToken(): String

    suspend fun clearData()
    suspend fun setBiometricsStatus(status: BiometricsStatus)

}
