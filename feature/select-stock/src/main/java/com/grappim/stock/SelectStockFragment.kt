package com.grappim.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.core.MainViewModel
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectStockFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    private val mainViewModel: MainViewModel by activityViewModels()

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
        val viewModel: SelectStockViewModel = viewModel()

        LoaderDialogCompose(show = viewModel.loading) {

        }

        if (viewModel.error != null) {
            showToast(getErrorMessage(viewModel.error!!))
        }

        SelectStockScreen(
            onBackButtonPressed = {
                findNavController().popBackStack()
            },
            stockProgressItems = viewModel.stockProgresses,
            stockItems = viewModel.stocks,
            onRefresh = viewModel::getStocks,
            selectStock = viewModel::selectStock,
            selectedStock = viewModel.selectedStock,
            onNextClick = {
                viewModel.saveStock()
                mainViewModel.stopSync()
                mainViewModel.startSync()
                navigator.navigateToFlow(NavigationFlow.SelectInfoCashierFlow)
            },
            isLoading = viewModel.loading
        )
    }
}