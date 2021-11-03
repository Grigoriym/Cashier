package com.grappim.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val basketCount by viewModel.basketCount.observeAsState("")
        val searchQuery by viewModel.searchQuery.collectAsState("")

        SalesScreen(
            onBackClick = {
                findNavController().popBackStack()
            },
            onScanClick = {
                viewModel.showScanner()
            },
            onBagClick = {
                viewModel.showBasket()
            },
            bagCount = basketCount,
            items = productItems,
            onMinusClick = {
                viewModel.subtractProduct(it)
            },
            onPlusClick = {
                viewModel.addProduct(it)
            },
            onCartClick = {
                viewModel.onCartClicked(it)
            },
            searchText = searchQuery,
            setSearchText = {
                viewModel.setQuery(it)
            }
        )
    }
}