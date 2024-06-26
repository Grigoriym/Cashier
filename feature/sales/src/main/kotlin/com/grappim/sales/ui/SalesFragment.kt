package com.grappim.sales.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.core.base.BaseFlowFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.sales.di.DaggerSalesComponent
import com.grappim.sales.di.SalesComponent
import com.grappim.uikit.theme.CashierTheme

class SalesFragment : BaseFlowFragment<SalesViewModel>() {

    private val component: SalesComponent by lazy {
        DaggerSalesComponent
            .factory()
            .create(
                salesDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: SalesViewModel by viewModels {
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
                SalesFragmentScreen()
            }
        }
    }

    @Composable
    private fun SalesFragmentScreen() {
        val productItems = viewModel.products.collectAsLazyPagingItems()
        val basketCount by viewModel.basketCount.collectAsState()
        val searchQuery by viewModel.searchQuery.collectAsState()
        val productsChanged by viewModel.productChanged.collectAsState()
        LaunchedEffect(productsChanged) {
            if (productsChanged) {
                productItems.refresh()
                viewModel.setProductChanged(false)
            }
        }

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
            setSearchText = viewModel::setQuery,
            basketProducts = emptyList()
        )
    }
}
