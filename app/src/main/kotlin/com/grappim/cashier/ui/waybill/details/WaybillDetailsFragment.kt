package com.grappim.cashier.ui.waybill.details

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.cashier.R
import com.grappim.cashier.core.delegate.lazyArg
import com.grappim.cashier.core.extensions.*
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.cashier.ui.theme.CashierTheme
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import com.grappim.cashier.ui.waybill.product.WaybillProductFragment
import com.grappim.date_time.DateTimeUtils
import com.grappim.date_time.getOffsetDateTimeWithFormatter
import com.grappim.date_time.toUtc
import com.grappim.domain.base.Result
import com.grappim.domain.model.waybill.Waybill
import com.grappim.calculations.bigDecimalZero
import com.grappim.date_time.DateTimeStandard
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
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

    private val dtfFull: DateTimeFormatter = DateTimeUtils.getDateTimeFormatterForFull()

    private val totalCost: BigDecimal? by lazyArg(ARG_TOTAL_COST)

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
        state: Result<Waybill>?
    ) {
        when (state) {
            is Result.Success -> {
                findNavController()
                    .navigate(
                        WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillList()
                    )
            }
            is Result.Error -> {
                showToast(getErrorMessage(state.exception))
            }
        }
    }

    @Composable
    private fun WaybillDetailsFragmentScreen() {
        val viewModel: WaybillDetailsViewModel = viewModel()
        val sharedViewModel: WaybillSharedViewModel by hiltNavGraphViewModels(R.id.nav_graph_waybill)

        val productItems = viewModel.products.collectAsLazyPagingItems()

        val waybillUpdateState by viewModel.waybillUpdate
        val waybill by sharedViewModel.waybill

        viewModel.setWaybillId(waybill!!.id)
        sharedViewModel.setTotalCost(totalCost ?: bigDecimalZero())

        handleWaybillUpdate(waybillUpdateState)

        WaybillDetailsScreen(
            waybill = waybill!!,
            productsPagingItems = productItems,
            onBackClick = {
                findNavController().popBackStack()
            },
            onSearchClick = {
                findNavController()
                    .navigate(WaybillDetailsFragmentDirections.actionWaybillToSearch(waybill!!.id))
            },
            onScanClick = {
                findNavController().navigate(
                    WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillScanner(
                        waybill!!.id
                    )
                )
            },
            onActionClick = {
                viewModel.updateWaybill(sharedViewModel.waybill.value!!)
            },
            onCommentSet = { comment ->
                sharedViewModel.setComment(comment)
            },
            onProductClick = { waybillProduct ->
                findNavController()
                    .navigate(
                        R.id.action_waybill_to_product,
                        bundleOf(
                            WaybillProductFragment.ARG_WAYBILL_ID to waybill!!.id,
                            WaybillProductFragment.ARG_WAYBILL_PRODUCT to waybillProduct
                        )
                    )
            },
            onDateClick = {
                setActualDateTime(sharedViewModel = sharedViewModel)
            }
        )
    }

    private fun setActualDateTime(
        sharedViewModel: WaybillSharedViewModel
    ) {
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
                val parsedDt = fullDateTime.getOffsetDateTimeWithFormatter(false, dtf)
                val parsedDtToUtc = parsedDt.toUtc()
                sharedViewModel.setReservedTime(dtfFull.format(parsedDtToUtc))
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