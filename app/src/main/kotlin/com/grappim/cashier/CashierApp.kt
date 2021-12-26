package com.grappim.cashier

import android.app.Application
import androidx.work.Configuration
import com.grappim.cashier.di.ApplicationComponent
import com.grappim.cashier.di.DaggerApplicationComponent
import com.grappim.di.ComponentDependenciesProvider
import com.grappim.di.deps.HasComponentDeps
import javax.inject.Inject

class CashierApp : Application(), Configuration.Provider,
    HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var workerConfiguration: Configuration

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        workerConfiguration
}