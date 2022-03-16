package com.grappim.menu.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.base.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.menu.di.DaggerMenuComponent
import com.grappim.menu.di.MenuComponent
import com.grappim.menu.ui.viewmodel.MenuViewModel
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class MenuFragment : BaseFragment<MenuViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: MenuViewModel by viewModels {
        viewModelFactory
    }

    private var _menuComponent: MenuComponent? = null
    private val menuComponent
        get() = requireNotNull(_menuComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _menuComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _menuComponent = DaggerMenuComponent
            .builder()
            .menuDeps(findComponentDependencies())
            .build()
        menuComponent.inject(this)
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
            onBackButtonPressed = viewModel::onBackPressed2
        )
    }

}