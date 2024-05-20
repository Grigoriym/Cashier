package com.grappim.cashier.feature.productcategory.presentation.createedit.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.feature.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        CreateEditProductCategoryBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        CreateEditProductCategoryDeps::class
    ]
)
]
interface CreateEditProductCategoryComponent {

    fun createEditViewModelAssistedFactory(): CreateEditProductCategoryViewModelImpl.Factory
    fun flowRouter(): FlowRouter
}
