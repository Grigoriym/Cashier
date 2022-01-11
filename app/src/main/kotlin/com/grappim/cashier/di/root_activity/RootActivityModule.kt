package com.grappim.cashier.di.root_activity

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.grappim.cashier.R
import com.grappim.common.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class RootActivityModule {

    @Provides
    @ActivityScope
    fun provideNavController(
        fragmentManager: FragmentManager
    ): NavController {
        val navHostFragment =
            fragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

}