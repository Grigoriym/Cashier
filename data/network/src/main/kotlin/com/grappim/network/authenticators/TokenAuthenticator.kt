package com.grappim.network.authenticators

import com.grappim.common.di.NetworkScope
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logD
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@NetworkScope
class TokenAuthenticator @Inject constructor(
    private val generalStorage: GeneralStorage
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            runBlocking {
                logD("${this@TokenAuthenticator::class.java.simpleName} authenticate")
                generalStorage.clearData()
                generalStorage.setAuthErrorFlow(true)
            }
            return null
        }
    }
}
