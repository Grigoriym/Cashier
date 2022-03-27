package com.grappim.menu.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.menu.di.DaggerMenuComponent
import com.grappim.menu.di.MenuComponent
import com.grappim.menu.ui.viewmodel.MenuViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.theme.CashierTheme

class MenuFragment : BaseFlowFragment<MenuViewModel>() {

    private val component: MenuComponent by lazy {
        DaggerMenuComponent
            .factory()
            .create(
                menuDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.viewModelFactory()
    }

    override val viewModel: MenuViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                MenuFragmentScreen()
            }
        }
    }

    @Composable
    private fun MenuFragmentScreen() {
        val cashierName by viewModel.cashierName.collectAsState()
        val items by viewModel.menuItems.collectAsState()

        MenuScreen(
            cashierName = cashierName,
            items = items,
            onItemClick = viewModel::onItemClick,
            onBackButtonPressed = viewModel::onBackPressed
        )
    }

}