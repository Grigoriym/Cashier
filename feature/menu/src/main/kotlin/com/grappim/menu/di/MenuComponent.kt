package com.grappim.menu.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.menu.ui.view.MenuFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        MenuBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        MenuDeps::class
    ]
)]
internal interface MenuComponent {

    fun inject(menuFragment: MenuFragment)

}