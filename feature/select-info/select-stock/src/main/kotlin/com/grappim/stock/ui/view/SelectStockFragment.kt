package com.grappim.stock.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.grappim.cashier.core.MainViewModel
import com.grappim.cashier.core.base.BaseFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.stock.di.DaggerSelectStockComponent
import com.grappim.stock.di.SelectStockComponent
import com.grappim.stock.ui.viewmodel.SelectStockViewModel
import com.grappim.uikit.theme.CashierTheme

class SelectStockFragment : BaseFragment<SelectStockViewModel>() {

    private val selectStockComponent: SelectStockComponent by lazy {
        DaggerSelectStockComponent
            .builder()
            .selectStockDeps(findComponentDependencies())
            .build()
    }

    override val flowRouter: FlowRouter by lazy {
        selectStockComponent.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        selectStockComponent.multiViewModelFactory()
    }

    private val mainViewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }
    override val viewModel: SelectStockViewModel by viewModels {
        viewModelFactory
    }
    private val selectInfoViewModel: SelectInfoViewModel by viewModels(
        ownerProducer = {
            requireParentFragment()
        },
        factoryProducer = {
            viewModelFactory
        }
    )

    override fun onBackPressed() {
        viewModel.clearData()
        super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SelectStockFragmentScreen()
            }
        }
    }

    @Composable
    private fun SelectStockFragmentScreen() {
        val stocksResult by viewModel.stocksResult.collectAsState()
        val loading by viewModel.loading.observeAsState(false)
        val selectedStock by viewModel.selectedStock.collectAsState()

        SelectStockScreen(
            onBackButtonPressed = {
                onBackPressed()
            },
            stockProgressItems = viewModel.stockProgresses,
            stockItems = stocksResult,
            onRefresh = viewModel::getStocks,
            selectStock = viewModel::selectStock,
            selectedStock = selectedStock,
            onNextClick = {
                viewModel.saveStock()
                mainViewModel.restartSync()
                selectInfoViewModel.goToSelectCashBox()
            },
            isLoading = loading
        )
    }
}
