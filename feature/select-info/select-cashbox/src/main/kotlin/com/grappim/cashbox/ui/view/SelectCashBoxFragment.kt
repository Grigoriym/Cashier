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
import com.grappim.core.base.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.select_info.common_navigation.SelectInfoViewModel
import com.grappim.uikit.theme.CashierTheme

class SelectCashBoxFragment : BaseFragment<SelectCashBoxViewModel>() {

    private val selectCashBoxComponent by lazy {
        DaggerSelectCashBoxComponent
            .builder()
            .selectCashBoxDeps(findComponentDependencies())
            .build()
    }

    override val flowRouter: FlowRouter by lazy {
        selectCashBoxComponent.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        selectCashBoxComponent.multiViewModelFactory()
    }
    private val rootViewModel: SelectInfoViewModel by viewModels(
        ownerProducer = {
            requireParentFragment()
        },
        factoryProducer = {
            viewModelFactory
        }
    )

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
            onBackButtonPressed = rootViewModel::backToSelectStock,
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