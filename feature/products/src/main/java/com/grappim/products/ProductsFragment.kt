package com.grappim.products

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
class ProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                ProductsFragmentScreen()
            }
        }
    }

    @Composable
    private fun ProductsFragmentScreen() {
        val viewModel: ProductsViewModel = viewModel()

        val categories by viewModel.categories.collectAsState()
        val products by viewModel.products.collectAsState(initial = emptyList())
        val searchQuery by viewModel.query.collectAsState()
        val index by viewModel.selectedIndex.collectAsState()

        ProductsScreen(
            onBackPressed = viewModel::onBackPressed,
            onCreateProductClick = viewModel::showCreateProduct,
            searchText = searchQuery,
            setSearchText = viewModel::searchProducts,
            categories = categories,
            selectedIndex = index,
            onTabClick = { idx, category ->
                viewModel.setCategory(category, idx)
            },
            products = products,
            onProductClick = viewModel::showEditProduct
        )
    }
}