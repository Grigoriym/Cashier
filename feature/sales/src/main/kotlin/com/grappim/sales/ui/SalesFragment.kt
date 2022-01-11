package com.grappim.sales.ui

import android.content.Context
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
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.sales.di.DaggerSalesComponent
import com.grappim.sales.di.SalesComponent
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class SalesFragment : BaseFragment<SalesViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: SalesViewModel by viewModels {
        viewModelFactory
    }

    private var _salesComponent: SalesComponent? = null
    private val salesComponent
        get() = requireNotNull(_salesComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _salesComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _salesComponent = DaggerSalesComponent
            .builder()
            .salesDeps(findComponentDependencies())
            .build()
        salesComponent.inject(this)
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
            onBackClick = viewModel::onBackPressed,
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