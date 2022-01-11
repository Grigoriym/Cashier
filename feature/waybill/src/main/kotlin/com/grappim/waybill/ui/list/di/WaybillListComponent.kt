package com.grappim.waybill.ui.list.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.date_time.DateTimeModule
import com.grappim.waybill.ui.list.ui.WaybillListFragment
import dagger.Component

@[FragmentScope Component(
    modules = [
        WaybillListBindsModule::class,
        CoroutinesModule::class,
        DateTimeModule::class
    ],
    dependencies = [
        WaybillListDeps::class
    ]
)]
interface WaybillListComponent {

    fun inject(waybillListFragment: WaybillListFragment)

}