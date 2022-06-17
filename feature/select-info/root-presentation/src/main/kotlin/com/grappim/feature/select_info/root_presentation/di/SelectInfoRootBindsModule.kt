package com.grappim.feature.select_info.root_presentation.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.feature.select_info.root_presentation.ui.SelectInfoViewModelImpl
import com.grappim.feature.select_info.common_navigation.SelectInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectInfoRootBindsModule {

    @[Binds IntoMap ViewModelKey(SelectInfoViewModel::class)]
    fun bindSelectInfoRootViewModel(
        selectInfoViewModel: SelectInfoViewModelImpl
    ): ViewModel

}