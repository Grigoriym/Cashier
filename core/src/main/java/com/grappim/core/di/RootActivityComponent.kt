package com.grappim.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.core.ui.MainActivity
import com.grappim.core.ui.MainViewModel
import com.grappim.di.ActivityScope
import com.grappim.navigation.Navigator
import com.grappim.workers.WorkerHelper
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[ActivityScope Component(
    modules = [
        RootActivityBindsModule::class
    ],
    dependencies = [
        RootActivityDeps::class
    ]
)]
interface RootActivityComponent {

    @Component.Builder
    interface Builder {
        fun deps(rootActivityDeps: RootActivityDeps): Builder

        fun build(): RootActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}

interface RootActivityDeps : ComponentDeps {
    fun navigator(): Navigator
    fun workerHelper(): WorkerHelper
}

@Module
interface RootActivityBindsModule {
    @[Binds IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}