package com.grappim.feature.selectinfo.rootpresentation.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoViewModel
import com.grappim.feature.selectinfo.rootpresentation.ui.SelectInfoViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectInfoRootBindsModule {

    @[Binds IntoMap ViewModelKey(SelectInfoViewModel::class)]
    fun bindSelectInfoRootViewModel(selectInfoViewModel: SelectInfoViewModelImpl): ViewModel
}
