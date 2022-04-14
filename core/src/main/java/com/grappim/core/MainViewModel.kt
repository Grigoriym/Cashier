package com.grappim.core

import com.grappim.core.base.BaseActivityViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class MainViewModel : BaseActivityViewModel() {

    abstract fun startSync()
    abstract fun stopSync()
    abstract fun restartSync()
    abstract fun goToAuth()
    abstract val isAuthError: StateFlow<Boolean>
}