package com.grappim.product_category.presentation.create_edit.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.product_category.presentation.create_edit.ui.CreateEditProductCategoryFragment
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

    fun inject(createEditProductCategoryFragment: CreateEditProductCategoryFragment)

}