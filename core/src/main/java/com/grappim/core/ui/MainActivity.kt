package com.grappim.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.grappim.core.R
import com.grappim.core.di.DaggerRootActivityComponent
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.logger.logD
import com.grappim.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    companion object {
        fun start(
            context: Context
        ) = context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
        setNavController()
        logD("${this} viewModel: mainViewModel: $viewModel")
    }

    private fun setNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        viewModel.setNavController(navController)
    }

    private fun performInject() {
        DaggerRootActivityComponent
            .builder()
            .deps(findComponentDependencies())
            .build()
            .inject(this)
    }

}