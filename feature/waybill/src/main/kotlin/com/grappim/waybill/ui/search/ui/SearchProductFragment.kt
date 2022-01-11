package com.grappim.waybill.ui.search.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.common.lce.Try
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.search.di.DaggerSearchWaybillProductComponent
import com.grappim.waybill.ui.search.di.SearchWaybillProductComponent
import javax.inject.Inject

class SearchProductFragment : BaseFragment<SearchProductViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel by viewModels<SearchProductViewModel> {
        viewModelFactory
    }

    private var _searchWaybillProductComponent: SearchWaybillProductComponent? = null
    private val searchWaybillProductComponent
        get() = requireNotNull(_searchWaybillProductComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _searchWaybillProductComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _searchWaybillProductComponent = DaggerSearchWaybillProductComponent
            .builder()
            .searchWaybillProductDeps(findComponentDependencies())
            .build()
        searchWaybillProductComponent.inject(this)
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
        val searchText by viewModel.searchText.collectAsState()
        val products by viewModel.productsFlow.collectAsState()
        val productState by viewModel.product.collectAsState()
        val waybillProductState by viewModel.waybillProduct.collectAsState()

        LoaderDialogCompose(
            show = productState is com.grappim.common.lce.Try.Loading ||
              waybillProductState is com.grappim.common.lce.Try.Loading
        )

        LaunchedEffect(key1 = productState, block = {})
        LaunchedEffect(key1 = waybillProductState, block = {})

        SearchProductScreen(
            onBackClick = viewModel::onBackPressed,
            searchText = searchText,
            setSearchText = viewModel::setSearchText,
            products = products,
            onProductClick = viewModel::checkProductInWaybill
        )
    }
}