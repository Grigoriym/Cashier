package com.grappim.core.base

import androidx.lifecycle.ViewModel
import com.grappim.navigation.router.ActivityRouter

abstract class BaseActivityViewModel : ViewModel() {

    protected lateinit var activityRouter: ActivityRouter

    fun setupActivityRouter(router: ActivityRouter) {
        activityRouter = router
    }
}