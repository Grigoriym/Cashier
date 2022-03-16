package com.grappim.waybill.ui.root.di

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.waybill.R
import dagger.Module
import dagger.Provides

@Module
object WaybillRootModule {

//    @Provides
//    @FeatureScope
//    @FeatureNavController
//    fun provideNavController(
//        @FeatureFragmentManager fragmentManager: FragmentManager
//    ): NavController {
//        val navHostFragment = fragmentManager
//            .findFragmentById(R.id.nav_host_waybill) as NavHostFragment
//        return navHostFragment.navController
//    }

}