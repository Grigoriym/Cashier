package com.grappim.waybill.ui.details.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.date_time.DateTimeModule
import com.grappim.waybill.ui.details.ui.WaybillDetailsFragment
import dagger.Component

@[FragmentScope Component(
    modules = [
        DecimalFormatModule::class,
        CoroutinesModule::class,
        DateTimeModule::class,
        WaybillDetailsBindsModule::class
    ],
    dependencies = [
        WaybillDetailsDeps::class
    ]
)]
interface WaybillDetailsComponent {

    fun inject(waybillDetailsFragment: WaybillDetailsFragment)

}