package com.grappim.cashier.ui.waybill.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.databinding.FragmentWaybillListBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import com.grappim.cashier.ui.waybill.details.WaybillDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.coroutines.flow.collectLatest
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class WaybillListFragment : Fragment(R.layout.fragment_waybill_list), WaybillListClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewModel: WaybillListViewModel by viewModels()
    private val sharedViewModel by navGraphViewModels<WaybillSharedViewModel>(R.id.nav_graph_waybill)
    private val viewBinding: FragmentWaybillListBinding by viewBinding(FragmentWaybillListBinding::bind)
    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }

    private val waybillListPagingAdapter: WaybillListPagingAdapter by lazy {
        WaybillListPagingAdapter(this, dfSimple)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(viewBinding) {
            buttonMenu.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            recyclerAcceptance.adapter = ScaleInAnimationAdapter(waybillListPagingAdapter)
            buttonCreateAcceptance.setSafeOnClickListener {
                viewModel.createWaybill()
            }
            swipeRefresh.setOnRefreshListener {
                waybillListPagingAdapter.refresh()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            waybillListPagingAdapter.loadStateFlow.collectLatest {
                viewBinding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.acceptances.collectLatest {
                waybillListPagingAdapter.submitData(it)
            }
        }
        viewModel.waybill.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    onWaybillClick(it.data)
                }
                is Resource.Error -> {
                    showToast(getErrorMessage(it.exception))
                }
            }
        }
    }

    override fun onWaybillClick(waybill: Waybill) {
        sharedViewModel.setCurrentWaybill(waybill)
        findNavController()
            .navigate(
                R.id.action_waybill_to_waybillDetails
            )
    }
}