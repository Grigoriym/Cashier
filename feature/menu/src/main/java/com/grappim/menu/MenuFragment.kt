package com.grappim.menu

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
import com.grappim.uikit.theme.CashierTheme
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

    @Composable
    private fun MenuFragmentScreen() {
        val viewModel: MenuViewModel = viewModel()
        val cashierName by viewModel.cashierName.collectAsState()
        val items by viewModel.menuItems.collectAsState()
        MenuScreen(
            cashierName = cashierName,
            items = items,
            onItemClick = { menuItem: MenuItemPm ->
                viewModel.onItemClick(menuItem)
            },
            onBackButtonPressed = {
                viewModel.onBackPressed()
            }
        )
    }
}