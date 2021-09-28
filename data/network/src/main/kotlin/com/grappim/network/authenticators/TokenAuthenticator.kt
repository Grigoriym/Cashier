package com.grappim.network.authenticators

import com.grappim.domain.di.MainDispatcher
import com.grappim.domain.storage.GeneralStorage
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
//    private val focusedActivityHolder: FocusedActivityHolder,
    private val generalStorage: GeneralStorage,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            generalStorage.clearData()
            navigateToAuth()
            return null
        }
    }

    private fun navigateToAuth() {
//        focusedActivityHolder.getCurrentActivity()?.let { activity ->
//            runBlocking(mainDispatcher) {
//                activity.findNavController(R.id.nav_host_fragment).popBackStack(
//                    R.id.authFragment,
//                    true
//                )
//            }
//        }
    }
}