package com.grappim.waybill.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.domain.base.Try
import com.grappim.domain.model.waybill.Waybill
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.WaybillSharedViewModel

class WaybillListFragment : Fragment() {

    private val sharedViewModel by viewModels<WaybillSharedViewModel>()

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

    private fun handleCreateState(
        createState: Try<Waybill>
    ) {
        when (createState) {
            is Try.Error -> {
                showToast(getErrorMessage(createState.exception))
            }
        }
    }

    @Composable
    private fun WaybillListFragmentScreen() {
        val viewModel: WaybillListViewModel = viewModel()

        val createState by viewModel.waybill.observeAsState(Try.Initial)
        val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        val searchText by viewModel.searchText.collectAsState()

        handleCreateState(
            createState = createState
        )

        LoaderDialogCompose(show = createState is Try.Loading)

        WaybillListScreen(
            onBackButtonPressed = sharedViewModel::onBackPressed,
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