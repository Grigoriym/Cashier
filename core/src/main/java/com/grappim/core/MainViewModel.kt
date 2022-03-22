package com.grappim.core

import androidx.lifecycle.ViewModel
import com.grappim.domain.model.FeatureToggle
import kotlinx.coroutines.flow.StateFlow

abstract class MainViewModel : ViewModel() {

    abstract fun startSync()
    abstract fun stopSync()
    abstract fun restartSync()
    abstract fun goToAuth()
    abstract val isAuthError: StateFlow<Boolean>
}