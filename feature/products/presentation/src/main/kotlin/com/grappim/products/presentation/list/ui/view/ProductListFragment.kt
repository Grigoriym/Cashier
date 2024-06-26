package com.grappim.products.presentation.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.cashier.core.base.BaseFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.products.presentation.list.di.DaggerProductsListComponent
import com.grappim.products.presentation.list.di.ProductsListComponent
import com.grappim.products.presentation.list.ui.viewmodel.ProductListViewModel
import com.grappim.uikit.theme.CashierTheme

class ProductListFragment : BaseFragment<ProductListViewModel>() {

    private val productListComponent: ProductsListComponent by lazy {
        DaggerProductsListComponent
            .builder()
            .productListDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        productListComponent.multiViewModelFactory()
    }

    override val viewModel: ProductListViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        productListComponent.flowRouter()
    }

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
