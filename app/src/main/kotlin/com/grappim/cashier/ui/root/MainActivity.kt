package com.grappim.cashier.ui.root

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.grappim.cashier.di.root_activity.DaggerRootActivityComponent
import androidx.appcompat.app.AppCompatActivity
import com.grappim.cashier.R
import com.grappim.cashier.di.root_activity.RootActivityComponent
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.di.ComponentDependenciesProvider
import com.grappim.di.deps.HasComponentDeps
import com.grappim.logger.logD
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
                rootActivityDeps = findComponentDependencies()
            )

        activityComponent.inject(this)
    }

    override fun onDestroy() {
        _activityComponent = null
        super.onDestroy()
    }

}