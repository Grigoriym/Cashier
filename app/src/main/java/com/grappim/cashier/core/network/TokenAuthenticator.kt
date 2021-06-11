package com.grappim.cashier.core.network

import androidx.navigation.findNavController
import com.grappim.cashier.R
import com.grappim.cashier.core.platform.FocusedActivityHolder
import com.grappim.cashier.core.storage.GeneralStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val focusedActivityHolder: FocusedActivityHolder,
    private val generalStorage: GeneralStorage
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            generalStorage.clearData()
            navigateToAuth()
            return null
        }
    }

    private fun navigateToAuth() {
        focusedActivityHolder.getCurrentActivity()?.let { activity ->
            runBlocking(Dispatchers.Main) {
                activity.findNavController(R.id.nav_host_fragment).popBackStack(
                    R.id.authFragment,
                    true
                )
            }
        }
    }
}