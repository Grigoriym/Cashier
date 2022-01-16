package com.grappim.cashbox.ui.view

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
import com.grappim.cashbox.di.SelectCashBoxComponent
import com.grappim.cashbox.ui.viewmodel.SelectCashBoxViewModel
import com.grappim.cashbox.ui.viewmodel.SelectCashBoxViewModelImpl
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class SelectCashBoxFragment : BaseFragment<SelectCashBoxViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: SelectCashBoxViewModel by viewModels {
        viewModelFactory
    }

    private var _selectCashBoxComponent: SelectCashBoxComponent? = null
    private val selectCashBoxComponent
        get() = requireNotNull(_selectCashBoxComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _selectCashBoxComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _selectCashBoxComponent = DaggerSelectCashBoxComponent
            .builder()
            .selectCashBoxDeps(findComponentDependencies())
            .build()
        selectCashBoxComponent.inject(this)
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