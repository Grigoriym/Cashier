package com.grappim.cashier

import android.app.Application
import android.os.StrictMode
import androidx.work.Configuration
import com.grappim.cashier.di.app.ApplicationComponent
import com.grappim.cashier.di.app.DaggerApplicationComponent
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import javax.inject.Inject

class CashierApp : Application(), Configuration.Provider,
    HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var workerConfiguration: Configuration

    override val workManagerConfiguration: Configuration
        get() = workerConfiguration

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
        appComponent.inject(this)
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }
}
