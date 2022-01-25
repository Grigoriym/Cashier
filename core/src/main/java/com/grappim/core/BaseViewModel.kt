package com.grappim.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    protected val _error = SingleLiveEvent<Throwable?>()
    val error: LiveData<Throwable?>
        get() = _error
}