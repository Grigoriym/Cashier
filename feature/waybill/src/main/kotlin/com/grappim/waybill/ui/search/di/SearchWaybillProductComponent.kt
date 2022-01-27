package com.grappim.waybill.ui.search.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FragmentScope Component(
    modules = [
        SearchWaybillProductBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SearchWaybillProductDeps::class
    ]
)]
interface SearchWaybillProductComponent {

    fun multiViewModelFactory(): MultiViewModelFactory

}