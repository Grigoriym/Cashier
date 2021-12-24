package com.grappim.cashier.di.component

import android.content.Context
import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.CashierApp
import com.grappim.cashier.di.modules.ApplicationModule
import com.grappim.cashier.di.modules.ComponentDepsModule
import com.grappim.core.di.RootActivityDeps
import com.grappim.date_time.DateTimeModule
import com.grappim.db.di.DatabaseModule
import com.grappim.di.AppScope
import com.grappim.di.ApplicationContext
import com.grappim.domain.di.CoroutinesModule
import com.grappim.domain.di.DomainModule
import com.grappim.network.di.ApiModule
import com.grappim.network.di.GsonModule
import com.grappim.network.di.NetworkModule
import com.grappim.repository.di.RepositoryModule
import com.grappim.repository.di.StorageModule
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
import com.grappim.workers.di.WorkersModule
import dagger.BindsInstance
import dagger.Component
import di.AuthDeps

@[AppScope Component(
    modules = [
        ApplicationModule::class,
        DateTimeModule::class,
        DatabaseModule::class,
        ApiModule::class,
        GsonModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class,
        DecimalFormatModule::class,
        DomainModule::class,
        CoroutinesModule::class,
        WorkersModule::class,
        ComponentDepsModule::class
    ],
    dependencies = [

    ]
)]
interface ApplicationComponent : AuthDeps,
    RootActivityDeps,
    SelectStockDeps,
    SignUpDeps,
    SelectCashBoxDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(@ApplicationContext context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(cashierApp: CashierApp)
}

