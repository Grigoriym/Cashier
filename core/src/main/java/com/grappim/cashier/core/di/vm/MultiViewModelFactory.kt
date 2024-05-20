package com.grappim.cashier.core.di.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MultiViewModelFactory @Inject constructor(
    private val factories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factories.getValue(modelClass as Class<ViewModel>).get() as T
    }

    val viewModelsClasses get() = factories.keys
}
