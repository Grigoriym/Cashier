package com.grappim.menu.di

import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.menu.ui.MenuFragment
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