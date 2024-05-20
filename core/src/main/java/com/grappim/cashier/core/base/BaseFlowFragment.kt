package com.grappim.cashier.core.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.github.terrakok.cicerone.Navigator
import com.grappim.cashier.core.navigation.CashierAppNavigator
import com.grappim.navigation.R

abstract class BaseFlowFragment<VM : BaseViewModel> : BaseFragment<VM> {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    @IdRes
    private val containerIdRes: Int = R.id.featureNavigationContainer

    private val navigator: Navigator by lazy {
        CashierAppNavigator(
            activity = requireActivity(),
            containerId = containerIdRes,
            fragmentManager = childFragmentManager
        )
    }

    /**
     * If you have a root fragment and some child fragments,
     * you need to make a replaceScreen on a flowRouter
     */
    open fun initialScreen() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowRouter.setContainerId(containerIdRes)
        if (childFragmentManager.findFragmentById(containerIdRes) == null) {
            initialScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        flowRouter.setNavigator(navigator)
    }

    override fun onPause() {
        flowRouter.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        flowRouter.flowOnBackPressed()
    }
}
