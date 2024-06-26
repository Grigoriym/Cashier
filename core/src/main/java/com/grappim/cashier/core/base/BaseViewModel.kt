package com.grappim.cashier.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.SingleLiveEvent
import com.grappim.navigation.router.FlowRouter

@Suppress("VariableNaming", "PropertyName")
abstract class BaseViewModel : ViewModel() {

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

    fun onBackPressed() {
        flowRouter.onBackPressed()
    }

    fun finishFlow() {
        flowRouter.finishFlow()
    }
}
