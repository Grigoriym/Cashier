package com.grappim.cashbox.ui.view

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
import com.grappim.cashbox.ui.viewmodel.SelectCashBoxViewModel
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.uikit.theme.CashierTheme

class SelectCashBoxFragment : BaseFragment<SelectCashBoxViewModel>() {

    private val selectCashBoxComponent by lazy {
        DaggerSelectCashBoxComponent
            .builder()
            .selectCashBoxDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        selectCashBoxComponent.multiViewModelFactory()
    }

    override val viewModel: SelectCashBoxViewModel by viewModels {
        viewModelFactory
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