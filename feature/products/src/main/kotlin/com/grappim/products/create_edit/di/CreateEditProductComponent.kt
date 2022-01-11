package com.grappim.products.create_edit.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.products.create_edit.ui.CreateEditProductFragment
import dagger.Component

@[FragmentScope Component(
    modules = [
        CreateEditProductBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        CreateEditProductDeps::class
    ]
)]
interface CreateEditProductComponent {

    fun inject(createEditProductFragment: CreateEditProductFragment)

}