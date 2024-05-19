package com.grappim.cashier.ui.root

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.Navigator
import com.grappim.cashier.R
import com.grappim.cashier.di.rootactivity.DaggerRootActivityComponent
import com.grappim.cashier.di.rootactivity.RootActivityComponent
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.MainViewModel
import com.grappim.core.base.BaseActivity
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.core.navigation.CashierAppNavigator
import com.grappim.logger.logD
import com.grappim.navigation.router.ActivityRouter
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main),
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

    override val activityRouter: ActivityRouter by lazy {
        component.activityRouter()
    }

    override val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    private val navigator: Navigator by lazy {
        CashierAppNavigator(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        viewModel.setupActivityRouter(activityRouter)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.goToAuth()
        }

        lifecycleScope.launch {
            viewModel.isAuthError
                .collect {
                    logD("isAuthError $it")
                    if (it) {
                        activityRouter.returnToInitialScreenOnAuthError()
                    }
                }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        activityRouter.setNavigator(navigator)
    }

    override fun onPause() {
        activityRouter.removeNavigator()
        super.onPause()
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        super.onBackPressed()
        activityRouter.onBackPressed()
    }
}
