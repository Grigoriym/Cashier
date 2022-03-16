package com.grappim.sales.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import com.grappim.sales.di.DaggerSalesComponent
import com.grappim.sales.di.SalesComponent
import com.grappim.uikit.theme.CashierTheme

class SalesFragment : BaseFlowFragment<SalesViewModel>() {

    private val component: SalesComponent by lazy {
        DaggerSalesComponent
            .builder()
            .salesDeps(findComponentDependencies())
            .build()
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
        val viewModel: SalesViewModel = viewModel()
        val productItems by viewModel.products.collectAsState()
        val basketCount by viewModel.basketCount.collectAsState()
        val searchQuery by viewModel.searchQuery.collectAsState()

        SalesScreen(
            onBackClick = viewModel::onBackPressed3,
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