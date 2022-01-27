package com.grappim.waybill.ui.search.ui.view

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
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.search.di.DaggerSearchWaybillProductComponent
import com.grappim.waybill.ui.search.di.SearchWaybillProductComponent
import com.grappim.waybill.ui.search.ui.viewmodel.SearchProductViewModel

class SearchProductFragment : BaseFragment<SearchProductViewModel>() {

    private val searchWaybillProductComponent: SearchWaybillProductComponent by lazy {
        DaggerSearchWaybillProductComponent
            .builder()
            .searchWaybillProductDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        searchWaybillProductComponent.multiViewModelFactory()
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
        val products by viewModel.productsFlow.collectAsState()

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