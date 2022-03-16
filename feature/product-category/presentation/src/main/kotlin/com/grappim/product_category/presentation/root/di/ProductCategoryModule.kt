package com.grappim.product_category.presentation.root.di

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.product_category.presentation.R
import dagger.Module
import dagger.Provides

@Module
class ProductCategoryModule {

//    @Provides
//    @FeatureScope
//    @FeatureNavController
//    fun provideNavController(
//        @FeatureFragmentManager fragmentManager: FragmentManager
//    ): NavController {
//        val navHostFragment =
//            fragmentManager.findFragmentById(R.id.featureNavigationContainer) as NavHostFragment
//        return navHostFragment.navController
//    }
}