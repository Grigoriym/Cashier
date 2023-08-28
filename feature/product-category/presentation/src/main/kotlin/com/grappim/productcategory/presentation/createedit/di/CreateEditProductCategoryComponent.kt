package com.grappim.productcategory.presentation.createedit.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.navigation.router.FlowRouter
import com.grappim.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import dagger.Component

@[FragmentScope Component(
    modules = [
        CreateEditProductCategoryBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        CreateEditProductCategoryDeps::class
    ]
)]
interface CreateEditProductCategoryComponent {

    fun createEditViewModelAssistedFactory(): CreateEditProductCategoryViewModelImpl.Factory
    fun flowRouter(): FlowRouter
}
