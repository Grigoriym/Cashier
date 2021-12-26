package com.grappim.cashier.di

import android.content.Context
import com.grappim.auth.di.AuthDeps
import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.CashierApp
import com.grappim.core.di.root_activity.RootActivityDeps
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

@[AppScope Component(
    modules = [
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
        AppComponentDepsModule::class
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

