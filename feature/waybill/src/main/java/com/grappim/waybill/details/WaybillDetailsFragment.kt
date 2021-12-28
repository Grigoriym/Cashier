package com.grappim.waybill.details

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.date_time.DateTimeStandard
import com.grappim.domain.base.Try
import com.grappim.domain.model.waybill.Waybill
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.padWithZeros
import com.grappim.extensions.showToast
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.root.WaybillSharedViewModel
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class WaybillDetailsFragment : Fragment() {

    @DecimalFormatSimple
    @Inject
    lateinit var dfSimple: DecimalFormat

    @Inject
    @DateTimeStandard
    lateinit var dtf: DateTimeFormatter

    companion object {
        const val ARG_TOTAL_COST = "arg_total_cost"
    }

//    private val totalCost: BigDecimal? by lazyArg(ARG_TOTAL_COST)

    private val viewModel by viewModels<WaybillDetailsViewModel>()
    private val sharedViewModel by viewModels<WaybillSharedViewModel>()

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

    private fun handleWaybillUpdate(
        state: Try<Waybill>
    ) {
        when (state) {
            is Try.Error -> {
                showToast(getErrorMessage(state.exception))
            }
        }
    }

    @Composable
    private fun WaybillDetailsFragmentScreen() {
        val productItems = viewModel.products.collectAsLazyPagingItems()

        val waybillUpdateState by viewModel.waybillUpdate
        val waybill by sharedViewModel.waybillFlow.collectAsState()
        val comment by viewModel.comment.collectAsState()
        val actualDate by viewModel.actualDate.collectAsState()

//        sharedViewModel.setTotalCost(totalCost ?: bigDecimalZero())

        handleWaybillUpdate(waybillUpdateState)

        WaybillDetailsScreen(
            waybill = waybill,
            productsPagingItems = productItems,
            onBackClick = sharedViewModel::onBackPressed,
            onSearchClick = sharedViewModel::showSearchProducts,
            onScanClick = viewModel::showScanner,
            onActionClick = {
                viewModel.updateWaybill(waybill)
            },
            onProductClick = sharedViewModel::showWaybillProduct,
            onDateClick = {
                setActualDateTime()
            },
            onRefresh = {

            },
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
            }, lHour, lMinute, true
        )

        val dpd = DatePickerDialog(
            requireActivity(),
            { _, year, month, dayOfMonth ->
                fullDate = "${dayOfMonth.padWithZeros(2)}.${(month + 1).padWithZeros(2)}.$year"
                tpd.show()
            }, lYear, lMonth, lDay
        )
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }

}