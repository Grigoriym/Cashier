package com.grappim.cashier.ui.root

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.grappim.cashier.R
import com.grappim.cashier.di.root_activity.DaggerRootActivityComponent
import com.grappim.cashier.di.root_activity.RootActivityComponent
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.MainViewModel
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.logger.logD
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private var _activityComponent: RootActivityComponent? = null
    val activityComponent
        get() = requireNotNull(_activityComponent)

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
        logD("${this} viewModel: mainViewModel: $viewModel")
    }

    private fun performInject() {
        _activityComponent = DaggerRootActivityComponent
            .factory()
            .create(
                context = this,
                fragmentManager = supportFragmentManager,
                rootActivityDeps = findComponentDependencies(),
                activity = this
            )

        activityComponent.inject(this)
    }

    override fun onDestroy() {
        _activityComponent = null
        super.onDestroy()
    }

}