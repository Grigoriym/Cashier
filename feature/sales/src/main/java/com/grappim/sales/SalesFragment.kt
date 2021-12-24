package com.grappim.sales

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

internal class SalesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SalesFragmentScreen()
            }
        }
    }

    @Composable
    private fun SalesFragmentScreen() {
        val viewModel: SalesViewModel = viewModel()
        val productItems by viewModel.products.collectAsState()
        val basketCount by viewModel.basketCount.collectAsState()
        val searchQuery by viewModel.searchQuery.collectAsState()

        SalesScreen(
            onBackClick = viewModel::onBackPressed,
            onScanClick = viewModel::showScanner,
            onBagClick = viewModel::showBasket,
            bagCount = basketCount,
            items = productItems,
            onMinusClick = viewModel::subtractProduct,
            onPlusClick = viewModel::addProduct,
            onCartClick = viewModel::onCartClicked,
            searchText = searchQuery,
            setSearchText = viewModel::setQuery
        )
    }
}