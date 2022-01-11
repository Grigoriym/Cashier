package com.grappim.waybill.ui.search.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.waybill.ui.search.ui.SearchProductFragment
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

    fun inject(searchProductFragment: SearchProductFragment)

}