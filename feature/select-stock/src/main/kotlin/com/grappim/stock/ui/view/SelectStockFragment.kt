package com.grappim.stock.ui.view

import android.content.Context
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
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.logger.logD
import com.grappim.stock.di.DaggerSelectStockComponent
import com.grappim.stock.di.SelectStockComponent
import com.grappim.stock.ui.viewmodel.SelectStockViewModel
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class SelectStockFragment : BaseFragment<SelectStockViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val mainViewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }
    override val viewModel: SelectStockViewModel by viewModels {
        viewModelFactory
    }

    private var _selectStockComponent: SelectStockComponent? = null
    private val selectStockComponent
        get() = requireNotNull(_selectStockComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _selectStockComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _selectStockComponent = DaggerSelectStockComponent
            .builder()
            .selectStockDeps(findComponentDependencies())
            .build()
        selectStockComponent.inject(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD("${this} viewModel | mainViewModel = $mainViewModel, viewModel = $viewModel")
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
                viewModel.showSelectInfo()
            },
            isLoading = loading
        )
    }
}