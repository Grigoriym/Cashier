package com.grappim.feature.waybill.presentation.ui.details.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.grappim.extensions.padWithZeros
import com.grappim.feature.waybill.presentation.ui.details.di.DaggerWaybillDetailsComponent
import com.grappim.feature.waybill.presentation.ui.details.di.WaybillDetailsComponent
import com.grappim.feature.waybill.presentation.ui.details.ui.viewmodel.WaybillDetailsViewModel
import com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel.WaybillRootViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import java.time.LocalDateTime
import java.util.Date

class WaybillDetailsFragment : BaseFragment<WaybillDetailsViewModel>() {

    private val component: WaybillDetailsComponent by lazy {
        DaggerWaybillDetailsComponent
            .builder()
            .waybillDetailsDeps(findComponentDependencies())
            .build()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel by viewModels<WaybillDetailsViewModel> {
        viewModelFactory
    }
    private val sharedViewModel by viewModels<WaybillRootViewModel>(
        ownerProducer = { requireParentFragment() },
        factoryProducer = {
            viewModelFactory
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                WaybillDetailsFragmentScreen()
            }
        }
    }

    @Composable
    private fun WaybillDetailsFragmentScreen() {
        val productItems = viewModel.products.collectAsLazyPagingItems()

        val loading by viewModel.loading.observeAsState(false)
        val waybill by sharedViewModel.waybillFlow.collectAsState()
        val comment by viewModel.comment.collectAsState()
        val actualDate by viewModel.actualDate.collectAsState()

        LoaderDialogCompose(show = loading)

//        sharedViewModel.setTotalCost(totalCost ?: bigDecimalZero())

        WaybillDetailsScreen(
            waybill = waybill,
            productsPagingItems = productItems,
            onBackClick = viewModel::onBackPressed,
            onSearchClick = viewModel::showSearchProducts,
            onScanClick = viewModel::showScanner,
            onActionClick = {
                viewModel.updateWaybill(waybill)
            },
            onProductClick = viewModel::showWaybillProduct,
            onDateClick = {
                setActualDateTime()
            },
            onRefresh = {},
            comment = comment,
            setComment = viewModel::setComment,
            actualDate = actualDate
        )
    }

    private fun setActualDateTime() {
        val now = LocalDateTime.now()
        val lYear = now.year
        val lMonth = now.monthValue
        val lDay = now.dayOfMonth
        val lHour = now.hour
        val lMinute = now.minute

        var fullDate = ""
        var fullTime: String
        val tpd = TimePickerDialog(
            requireActivity(),
            { _, hourOfDay, minute ->
                fullTime = "${hourOfDay.padWithZeros(2)}:${minute.padWithZeros(2)}"
                val fullDateTime = "$fullDate $fullTime"
                viewModel.setActualDate(fullDateTime)
            },
            lHour,
            lMinute,
            true
        )

        val dpd = DatePickerDialog(
            requireActivity(),
            { _, year, month, dayOfMonth ->
                fullDate = "${dayOfMonth.padWithZeros(2)}.${(month + 1).padWithZeros(2)}.$year"
                tpd.show()
            },
            lYear,
            lMonth,
            lDay
        )
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }

    companion object {
        fun newInstance() = WaybillDetailsFragment()
    }
}
