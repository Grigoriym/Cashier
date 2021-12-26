package com.grappim.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.grappim.core.R
import com.grappim.core.di.components_deps.findAppComponentDependencies
import com.grappim.core.di.root_activity.DaggerRootActivityComponent
import com.grappim.core.di.root_activity.RootActivityComponent
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.di.ComponentDependenciesProvider
import com.grappim.di.deps.HasComponentDeps
import com.grappim.logger.logD
import com.grappim.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HasComponentDeps {

    companion object {
        fun start(
            context: Context
        ) = context.startActivity(Intent(context, MainActivity::class.java))
    }

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    @Inject
    lateinit var navigator: Navigator

    private var _activityComponent: RootActivityComponent? = null
    private val activityComponent
        get() = requireNotNull(_activityComponent)

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
        logD("${this} viewModel: mainViewModel: $viewModel")
        navigator.setNavController(getNavController())
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

    private fun performInject() {
        _activityComponent = DaggerRootActivityComponent
            .builder()
            .deps(findAppComponentDependencies())
            .bindFragmentManager(supportFragmentManager)
            .build()

        activityComponent.inject(this)
    }

    override fun onDestroy() {
        _activityComponent = null
        super.onDestroy()
    }

}