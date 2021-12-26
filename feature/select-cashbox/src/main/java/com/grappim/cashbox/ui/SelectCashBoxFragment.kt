package com.grappim.cashbox.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.cashbox.di.DaggerSelectCashBoxComponent
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findActivityComponentDependencies
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.uikit.theme.CashierTheme

class SelectCashBoxFragment : BaseFragment<SelectCashBoxViewModel>() {

    override val viewModel: SelectCashBoxViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    private fun performInject(){
        DaggerSelectCashBoxComponent
            .builder()
            .deps(findComponentDependencies())
            .navDeps(findActivityComponentDependencies())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SelectCashierFragmentScreen()
            }
        }
    }

    @Composable
    private fun SelectCashierFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)

        SelectCashBoxScreen(
            onBackButtonPressed = viewModel::onBackPressed,
            cashBoxProgressItems = viewModel.cashBoxProgressItems,
            cashBoxItems = viewModel.cashBoxes,
            onRefresh = viewModel::getCashBoxes,
            selectCashBox = viewModel::selectCashBox,
            onNextClick = {
                viewModel.saveCashBox()
                viewModel.showMenu()
            },
            isLoading = loading,
            selectedCashBox = viewModel.selectedCashBox
        )
    }

}