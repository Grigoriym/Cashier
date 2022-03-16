package com.grappim.waybill.ui.search.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.waybill.ui.search.ui.viewmodel.SearchProductViewModel
import com.grappim.waybill.ui.search.ui.viewmodel.SearchProductViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchWaybillProductBindsModule {

    @[Binds IntoMap ViewModelKey(SearchProductViewModel::class)]
    fun bindWaybillListViewModel(
        searchProductViewModel: SearchProductViewModelImpl
    ): ViewModel

}