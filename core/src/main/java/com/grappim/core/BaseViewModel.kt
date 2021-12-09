package com.grappim.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    protected val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error
}