package com.grappim.cashier.ui.splash

import com.grappim.navigation.AppRouter
import com.grappim.core.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val appRouter: AppRouter
) : BaseViewModel() {

    fun goToAuth() {
        appRouter.goToAuth()
    }

}