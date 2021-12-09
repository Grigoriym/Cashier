package com.grappim.stock

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
import com.grappim.core.BaseFragment
import com.grappim.core.MainViewModel
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectStockFragment : BaseFragment<SelectStockViewModel>() {

    @Inject
    lateinit var navigator: Navigator

    private val mainViewModel: MainViewModel by activityViewModels()
    override val viewModel: SelectStockViewModel by viewModels()

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
            onBackButtonPressed = viewModel::onBackPressed,
            stockProgressItems = viewModel.stockProgresses,
            stockItems = stocksResult,
            onRefresh = viewModel::getStocks,
            selectStock = viewModel::selectStock,
            selectedStock = selectedStock,
            onNextClick = {
                viewModel.saveStock()
                mainViewModel.stopSync()
                mainViewModel.startSync()
                navigator.navigateToFlow(NavigationFlow.SelectInfoCashierFlow)
            },
            isLoading = loading
        )
    }
}