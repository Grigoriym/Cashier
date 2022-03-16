package com.grappim.cashier.core.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.grappim.common.di.ActivityScope
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
class NavigationManager @Inject constructor(
    private val navController: Lazy<NavController>,
    private val activity: AppCompatActivity
) {


}