package com.grappim.waybill.ui.list.ui.view

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
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.list.di.DaggerWaybillListComponent
import com.grappim.waybill.ui.list.di.WaybillListComponent
import com.grappim.waybill.ui.list.ui.viewmodel.WaybillListViewModel

class WaybillListFragment : BaseFragment<WaybillListViewModel>() {

    private val waybillListComponent: WaybillListComponent by lazy {
        DaggerWaybillListComponent
            .builder()
            .waybillListDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        waybillListComponent.multiViewModelFactory()
    }

    override val viewModel: WaybillListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                WaybillListFragmentScreen()
            }
        }
    }

    @Composable
    private fun WaybillListFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)
        val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        val searchText by viewModel.searchText.collectAsState()

        LoaderDialogCompose(show = loading)

        WaybillListScreen(
            onBackButtonPressed = viewModel::onBackPressed,
            onCreateAcceptanceClick = viewModel::createDraftWaybill,
            onWaybillClick = viewModel::showDetails,
            onRefresh = viewModel::refresh,
            lazyPagingItems = lazyPagingItems,
            isRefreshing = isRefreshing,
            searchText = searchText,
            setSearchText = viewModel::setSearchText
        )
    }
}