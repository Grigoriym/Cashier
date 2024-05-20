package com.grappim.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.common.di.ApplicationContext
import javax.inject.Inject

@AppScope
class NetworkHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val isConnected: Boolean
        get() {
            var result = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                        else -> false
                    }
                }
            }
            return result
        }
}
