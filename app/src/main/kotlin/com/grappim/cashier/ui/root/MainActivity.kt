package com.grappim.cashier.ui.root

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.cicerone.Navigator
import com.grappim.cashier.R
import com.grappim.cashier.di.root_activity.DaggerRootActivityComponent
import com.grappim.cashier.di.root_activity.RootActivityComponent
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.MainViewModel
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.core.navigation.CashierAppNavigator
import com.grappim.navigation.router.ActivityRouter

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HasComponentDeps {

    private val component: RootActivityComponent by lazy {
        DaggerRootActivityComponent
            .factory()
            .create(
                context = this,
                fragmentManager = supportFragmentManager,
                rootActivityDeps = findComponentDependencies(),
                activity = this
            )
    }

    override val deps: ComponentDependenciesProvider by lazy {
        component.deps()
    }
    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    private val activityRouter: ActivityRouter by lazy {
        component.activityRouter()
    }

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    private val navigator: Navigator by lazy {
        CashierAppNavigator(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel.goToAuth()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        activityRouter.setNavigator(navigator)
    }

    override fun onPause() {
        activityRouter.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        activityRouter.onBackPressed()
    }

}