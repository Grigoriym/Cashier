package com.grappim.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.grappim.core.SingleLiveEvent
import com.grappim.navigation.FlowRouter

abstract class BaseViewModel2 : ViewModel() {

    protected lateinit var flowRouter: FlowRouter

    protected val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    protected val _error = SingleLiveEvent<Throwable?>()
    val error: LiveData<Throwable?>
        get() = _error

    fun setupFlowRouter(router: FlowRouter) {
        flowRouter = router
    }

    fun onBackPressed3() {
        flowRouter.onBackPressed()
    }

    fun finishFlow() {
        flowRouter.finishFlow()
    }
}