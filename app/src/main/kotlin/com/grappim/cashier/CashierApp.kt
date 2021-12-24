package com.grappim.cashier

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.grappim.cashier.di.component.ApplicationComponent
import com.grappim.cashier.di.component.DaggerApplicationComponent
import com.grappim.core.di.components_deps.ComponentDependenciesProvider
import com.grappim.core.di.components_deps.HasComponentDeps
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

val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is CashierApp -> appComponent
        else -> applicationContext.appComponent
    }