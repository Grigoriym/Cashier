package com.grappim.cashier.di.app

import android.content.Context
import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.CashierApp
import com.grappim.cashier.di.root_activity.RootActivityDeps
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.*
import com.grappim.date_time.DateTimeModule
import com.grappim.db.di.DatabaseModule
import com.grappim.network.di.NetworkModule
import com.grappim.network.di.api.ApiModule
import com.grappim.network.di.gson.GsonModule
import com.grappim.repository.di.RepositoryModule
import com.grappim.repository.di.StorageModule
import com.grappim.workers.di.WorkersModule
import dagger.BindsInstance
import dagger.Component

@[AppScope GsonScope ApiScope NetworkScope DatabaseScope
Component(
    modules = [
        DateTimeModule::class,
        DatabaseModule::class,
        ApiModule::class,
        GsonModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class,
        DecimalFormatModule::class,
        CoroutinesModule::class,
        WorkersModule::class,
        AppComponentDepsModule::class
    ],
    dependencies = [

    ]
)]
interface ApplicationComponent : RootActivityDeps {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): ApplicationComponent
    }

    fun inject(cashierApp: CashierApp)
}

