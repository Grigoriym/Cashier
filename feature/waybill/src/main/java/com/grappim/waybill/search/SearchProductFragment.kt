package com.grappim.waybill.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grappim.domain.base.Try
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.WaybillSharedViewModel

class SearchProductFragment : Fragment() {

    private val viewModel by viewModels<SearchProductViewModel>()
    private val sharedViewModel by viewModels<WaybillSharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SearchProductFragmentScreen()
            }
        }
    }

    @Composable
    private fun SearchProductFragmentScreen() {
        val searchText by viewModel.searchText.collectAsState()
        val products by viewModel.productsFlow.collectAsState()
        val productState by viewModel.product.collectAsState()
        val waybillProductState by viewModel.waybillProduct.collectAsState()

        LoaderDialogCompose(
            show = productState is Try.Loading ||
              waybillProductState is Try.Loading
        )

        LaunchedEffect(key1 = productState, block = {})
        LaunchedEffect(key1 = waybillProductState, block = {})

        SearchProductScreen(
            onBackClick = sharedViewModel::onBackPressed,
            searchText = searchText,
            setSearchText = viewModel::setSearchText,
            products = products,
            onProductClick = viewModel::checkProductInWaybill
        )
    }
}