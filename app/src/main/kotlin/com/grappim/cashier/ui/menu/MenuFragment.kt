package com.grappim.cashier.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.cashier.R
import com.grappim.cashier.ui.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

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

    private fun onItemClick(item: MenuItem) {
        when (item.type) {
            MenuItemType.ACCEPTANCE -> {
                findNavController().navigate(R.id.action_menuFragment_to_acceptanceFragment)
            }
            MenuItemType.PRODUCTS -> {
                findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
            }
            MenuItemType.SALES -> {
                findNavController().navigate(R.id.action_menuFragment_to_salesFragment)
            }
        }
    }

    @Composable
    private fun MenuFragmentScreen() {
        val viewModel: MenuViewModel = viewModel()
        val cashierName by viewModel.cashierName.collectAsState()
        val items by viewModel.menuItems.collectAsState()
        MenuScreen(
            cashierName = cashierName,
            items = items,
            onItemClick = { menuItem: MenuItem ->
                onItemClick(menuItem)
            },
            onBackButtonPressed = {
                findNavController().popBackStack()
            }
        )
    }
}