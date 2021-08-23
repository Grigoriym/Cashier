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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.ui.theme.CashierTheme
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaybillListFragment : Fragment() {

    private val sharedViewModel by navGraphViewModels<WaybillSharedViewModel>(R.id.nav_graph_waybill)

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

    @Composable
    private fun WaybillListFragmentScreen() {
        val viewModel: WaybillListViewModel = viewModel()
        val createState by viewModel.waybill.observeAsState()

        val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        WaybillListScreen(
            onBackButtonPressed = {
                findNavController().popBackStack()
            },
            onCreateAcceptanceClick = {
                viewModel.createWaybill()
            },
            onWaybillClick = {
                sharedViewModel.setCurrentWaybill(it)
                findNavController()
                    .navigate(
                        R.id.action_waybill_to_waybillDetails
                    )
            },
            loaderDialog = loader,
            showError = {
                showToast(getErrorMessage(it))
            },
            createState = createState,
            onRefresh = { viewModel.refresh() },
            lazyPagingItems = lazyPagingItems,
            isRefreshing = isRefreshing
        )
    }
}