package com.grappim.cashier.ui.selectinfo.stock

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
import com.grappim.cashier.R
import com.grappim.cashier.compose.LoaderDialogCompose
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.ui.root.MainViewModel
import com.grappim.cashier.ui.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectStockFragment : Fragment() {

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
                findNavController().navigate(R.id.action_selectOutletFragment_to_selectCashierFragment)
            },
            isLoading = viewModel.loading
        )
    }
}