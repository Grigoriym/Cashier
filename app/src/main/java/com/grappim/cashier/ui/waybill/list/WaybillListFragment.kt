package com.grappim.cashier.ui.waybill.list

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
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.ui.theme.CashierTheme
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaybillListFragment : Fragment() {

    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
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

    private fun handleCreateState(
        createState: Resource<Waybill>?,
        onWaybillClick: (Waybill) -> Unit
    ) {
        loader.showOrHide(createState is Resource.Loading)

        when (createState) {
            is Resource.Success -> {
                val waybill = createState.data
                onWaybillClick(waybill)
            }
            is Resource.Error -> {
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