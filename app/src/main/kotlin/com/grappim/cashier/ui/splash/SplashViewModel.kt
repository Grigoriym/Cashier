package com.grappim.cashier.ui.splash

import com.grappim.cashier.di.splash.SplashScreenNavigator
import com.grappim.core.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashScreenNavigator: SplashScreenNavigator
) : BaseViewModel() {

    fun goToAuth() {
        splashScreenNavigator.goToAuthFromSplash()
    }

}