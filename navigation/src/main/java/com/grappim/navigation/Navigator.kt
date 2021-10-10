package com.grappim.navigation

import androidx.annotation.MainThread
import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(

) {

    private var _navController: NavController? = null
    private val navController: NavController
        get() = requireNotNull(_navController) {
            "navController must not be null"
        }

    fun setNavController(controller: NavController) {
        _navController = controller
    }

    @MainThread
    fun navigateToFlow(
        navigationFlow: NavigationFlow
    ) = when (navigationFlow) {
        NavigationFlow.MainFlow -> {
            navController.navigate(MainNavGraphDirections.actionAuthFlowToMainFlow())
        }
        NavigationFlow.WaybillFlow -> {
            navController.navigate(MainNavGraphDirections.actionMainFlowToWaybillFlow())
        }
    }
}