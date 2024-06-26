package com.grappim.feature.waybill.presentation.ui.search.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.core.base.BaseFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.feature.waybill.presentation.ui.search.di.DaggerSearchWaybillProductComponent
import com.grappim.feature.waybill.presentation.ui.search.di.SearchWaybillProductComponent
import com.grappim.feature.waybill.presentation.ui.search.ui.viewmodel.SearchProductViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class SearchProductFragment : BaseFragment<SearchProductViewModel>() {

    private val component: SearchWaybillProductComponent by lazy {
        DaggerSearchWaybillProductComponent
            .builder()
            .searchWaybillProductDeps(findComponentDependencies())
            .build()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel by viewModels<SearchProductViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SearchProductFragmentScreen()
            }
        }
    }

    @Composable
    private fun SearchProductFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)
        val searchText by viewModel.searchText.collectAsState()
        val products = viewModel.productsFlow.collectAsLazyPagingItems()

        LoaderDialogCompose(show = loading)

        SearchProductScreen(
            onBackClick = viewModel::onBackPressed,
            searchText = searchText,
            setSearchText = viewModel::setSearchText,
            products = products,
            onProductClick = viewModel::checkProductInWaybill
        )
    }
}
