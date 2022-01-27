package com.grappim.core

import androidx.lifecycle.ViewModel

abstract class MainViewModel : ViewModel() {

    abstract fun startSync()
    abstract fun stopSync()
    abstract fun restartSync()
}