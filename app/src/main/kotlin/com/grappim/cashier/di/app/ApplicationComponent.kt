package com.grappim.cashier.di.app

import android.content.Context
import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.CashierApp
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.ApiScope
import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.common.di.ApplicationContext
import com.grappim.cashier.common.di.DatabaseScope
import com.grappim.cashier.common.di.NetworkScope
import com.grappim.cashier.common.di.SerializationScope
import com.grappim.cashier.datetime.DateTimeModule
import com.grappim.cashier.di.rootactivity.RootActivityDeps
import com.grappim.db.di.DatabaseModule
import com.grappim.feature.auth.network.di.AuthApiModule
import com.grappim.feature.bag.presentation.di.BagAppModule
import com.grappim.feature.waybill.presentation.ui.details.di.WaybillAppModule
import com.grappim.network.di.NetworkModule
import com.grappim.network.di.api.ApiModule
import com.grappim.network.di.serialization.SerializationModule
import com.grappim.products.presentation.root.di.ProductsAppModule
import com.grappim.repository.di.RepositoryModule
import com.grappim.repository.di.StorageModule
import com.grappim.workers.di.WorkersBindsModule
import com.grappim.workers.di.WorkersModule
import dagger.BindsInstance
import dagger.Component

@[
AppScope ApiScope NetworkScope DatabaseScope SerializationScope
Component(
    modules = [
        DateTimeModule::class,
        DatabaseModule::class,
        ApiModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class,
        DecimalFormatModule::class,
        CoroutinesModule::class,
        WorkersBindsModule::class,
        WorkersModule::class,
        AppComponentDepsModule::class,
        SerializationModule::class,
        AnalyticsBindsModule::class,
        PasswordManagerBindModule::class,
        AuthApiModule::class,
        BagAppModule::class,
        ProductsAppModule::class,
        WaybillAppModule::class
    ]
)
]
interface ApplicationComponent : RootActivityDeps {

    @Component.Factory
    interface Factory {
        fun create(@[BindsInstance ApplicationContext] context: Context): ApplicationComponent
    }

    fun inject(cashierApp: CashierApp)
}
