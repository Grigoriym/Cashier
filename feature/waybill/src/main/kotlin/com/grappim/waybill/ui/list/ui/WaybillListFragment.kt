package com.grappim.waybill.ui.list.ui

import android.content.Context
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
import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.Waybill
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.list.di.DaggerWaybillListComponent
import com.grappim.waybill.ui.list.di.WaybillListComponent
import javax.inject.Inject

class WaybillListFragment : BaseFragment<WaybillListViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: WaybillListViewModel by viewModels {
        viewModelFactory
    }
//    private val sharedViewModel by viewModels<WaybillSharedViewModel>(
//        ownerProducer = {
//            requireParentFragment()
//        },
//        factoryProducer = {
//            viewModelFactory
//        }
//    )

    private var _waybillListComponent: WaybillListComponent? = null
    private val waybillListComponent
        get() = requireNotNull(_waybillListComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _waybillListComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _waybillListComponent = DaggerWaybillListComponent
            .builder()
            .waybillListDeps(findComponentDependencies())
            .build()
        waybillListComponent.inject(this)
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
        createState: com.grappim.common.lce.Try<Waybill>
    ) {
        when (createState) {
            is com.grappim.common.lce.Try.Error -> {
                showToast(getErrorMessage(createState.exception))
            }
        }
    }

    @Composable
    private fun WaybillListFragmentScreen() {
        val createState by viewModel.waybill.observeAsState(com.grappim.common.lce.Try.Initial)
        val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        val searchText by viewModel.searchText.collectAsState()

        handleCreateState(
            createState = createState
        )

        LoaderDialogCompose(show = createState is com.grappim.common.lce.Try.Loading)

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