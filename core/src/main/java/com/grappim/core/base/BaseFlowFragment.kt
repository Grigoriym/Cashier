package com.grappim.core.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.grappim.core.R
import com.grappim.core.navigation.CashierAppNavigator
import com.grappim.navigation.BackFragment

abstract class BaseFlowFragment<VM : BaseViewModel2> : BaseFragment2<VM> {

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

    open fun initialScreen() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowRouter.setFragmentManager(childFragmentManager)
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
        val fragment = childFragmentManager
            .findFragmentById(containerIdRes) as? BackFragment
        fragment?.onBackPressed() ?: viewModel.onBackPressed3()
    }
}