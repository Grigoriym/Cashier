package com.grappim.cashier.ui.waybill.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.R
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.uikit.theme.CashierTheme
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import com.grappim.domain.base.Result
import com.grappim.domain.model.waybill.Waybill
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaybillListFragment : Fragment() {

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
        createState: Result<Waybill>?,
        onWaybillClick: (Waybill) -> Unit
    ) {
        when (createState) {
            is Result.Success -> {
                val waybill = createState.data
                onWaybillClick(waybill)
            }
            is Result.Error -> {
                showToast(getErrorMessage(createState.exception))
            }
        }
    }

    @Composable
    private fun WaybillListFragmentScreen() {
        val viewModel: WaybillListViewModel = viewModel()
        val sharedViewModel: WaybillSharedViewModel by hiltNavGraphViewModels(R.id.nav_graph_waybill)
        val createState by viewModel.waybill

        val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        val onWaybillClick: (Waybill) -> Unit = { waybill ->
            viewModel.ductTape()
            sharedViewModel.setCurrentWaybill(waybill)
            findNavController()
                .navigate(
                    R.id.action_waybill_to_waybillDetails
                )
        }

        handleCreateState(
            createState = createState,
            onWaybillClick = {
                onWaybillClick(it)
            }
        )

        com.grappim.uikit.compose.LoaderDialogCompose(show = viewModel.loading) {}

        WaybillListScreen(
            onBackButtonPressed = {
                findNavController().popBackStack()
            },
            onCreateAcceptanceClick = {
                viewModel.createWaybill()
            },
            onWaybillClick = {
                onWaybillClick(it)
            },
            onRefresh = { viewModel.refresh() },
            lazyPagingItems = lazyPagingItems,
            isRefreshing = isRefreshing
        )
    }
}