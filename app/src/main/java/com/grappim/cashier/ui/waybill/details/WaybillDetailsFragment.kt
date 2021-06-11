package com.grappim.cashier.ui.waybill.details

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.delegate.lazyArg
import com.grappim.cashier.core.extensions.*
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.databinding.FragmentWaybillDetailsBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.domain.waybill.WaybillProduct
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import com.grappim.cashier.ui.waybill.WaybillStatus
import com.grappim.cashier.ui.waybill.product.WaybillProductFragment
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.coroutines.flow.collectLatest
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WaybillDetailsFragment : Fragment(R.layout.fragment_waybill_details) {

    @DecimalFormatSimple
    @Inject
    lateinit var dfSimple: DecimalFormat

    companion object {
        const val ARG_TOTAL_COST = "arg_total_cost"
    }

    private val dtfFull: DateTimeFormatter = DateTimeUtils.getDateTimeFormatterForFull()
    private val dtf: DateTimeFormatter = DateTimeUtils.getDateTimePatternStandard()

    private val viewBinding: FragmentWaybillDetailsBinding by viewBinding(
        FragmentWaybillDetailsBinding::bind
    )

    private val waybill: Waybill by lazy {
        sharedViewModel.waybill.value!!
    }
    private val viewModel by viewModels<WaybillDetailsViewModel>()
    private val sharedViewModel by navGraphViewModels<WaybillSharedViewModel>(R.id.nav_graph_waybill)
    private val waybillProductsAdapter by lazy {
        WaybillProductsAdapter(
            dfSimple,
            ::onProductClick
        )
    }
    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }
    private val totalCost: BigDecimal? by lazyArg(ARG_TOTAL_COST)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(viewBinding) {
            textWaybillNumber.text = getString(
                R.string.title_acceptance_number,
                waybill.number
            )
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            buttonSearch.setSafeOnClickListener {
                findNavController()
                    .navigate(WaybillDetailsFragmentDirections.actionWaybillToSearch(waybill.id))
            }
            buttonScan.setSafeOnClickListener {
                findNavController().navigate(
                    WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillScanner(
                        waybill.id
                    )
                )
            }
            editDate.setSafeOnClickListener {
                setActualDateTime()
            }
            recyclerProducts.adapter = ScaleInAnimationAdapter(waybillProductsAdapter)

            buttonAction.setSafeOnClickListener {
                viewModel.updateWaybill(sharedViewModel.waybill.value!!)
            }
            editComment.doAfterTextChanged {
                sharedViewModel.setComment(it.toString())
            }
            swipeRefresh.setOnRefreshListener {
                waybillProductsAdapter.refresh()
            }
            when (waybill.status) {
                WaybillStatus.ACTIVE -> {
                    buttonAction.text = getString(R.string.action_rollback)
                }
                WaybillStatus.DRAFT -> {
                    buttonAction.text = getString(R.string.action_conduct)
                }
            }
        }
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

    private fun onProductClick(product: WaybillProduct) {
        findNavController()
            .navigate(
                R.id.action_waybill_to_product,
                bundleOf(
                    WaybillProductFragment.ARG_WAYBILL_ID to waybill.id,
                    WaybillProductFragment.ARG_WAYBILL_PRODUCT to product
                )
            )
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            waybillProductsAdapter.loadStateFlow.collectLatest {
                viewBinding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
        sharedViewModel.waybill.observe(viewLifecycleOwner) {
            it.reservedTime?.let { reservedTime ->
                viewBinding.editDate.setText(dtf.format(reservedTime.getOffsetDateTimeFromString()))
            }
            it.totalCost.let { totalCost ->
                viewBinding.textPrice.text = dfSimple.format(totalCost)
            }
        }
        viewModel.setWaybillId(waybill.id)
        waybill.reservedTime?.let {
            sharedViewModel.setReservedTime(it)
        }
        waybill.comment.let {
            viewBinding.editComment.setText(it)
        }
        sharedViewModel.setTotalCost(totalCost ?: bigDecimalZero())

        lifecycleScope.launchWhenCreated {
            waybillProductsAdapter.addLoadStateListener {
                val itemsCount = waybillProductsAdapter.itemCount
                viewBinding.textLabelGoods.text = getString(
                    R.string.title_goods_acceptance,
                    itemsCount
                )
            }

            viewModel.products.collectLatest {
                waybillProductsAdapter.submitData(it)
            }
        }
        viewModel.waybillUpdate.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    findNavController()
                        .navigate(
                            WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillList()
                        )
                }
                is Resource.Error -> {
                    showToast(getErrorMessage(it.exception))
                }
            }
        }
    }

}