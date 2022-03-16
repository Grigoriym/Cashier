package com.grappim.core.navigation

import androidx.fragment.app.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grappim.navigation.R

class CashierAppNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
      fragmentTransaction.setCustomAnimations(
          R.anim.enter_from_right,
          R.anim.exit_to_left,
          R.anim.pop_enter_from_left,
          R.anim.pop_exit_to_right
      )
    }

}