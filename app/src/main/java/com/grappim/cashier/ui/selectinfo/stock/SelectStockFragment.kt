package com.grappim.cashier.ui.selectinfo.stock

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.databinding.FragmentSelectStockCashierBinding
import com.grappim.cashier.domain.outlet.Stock
import com.grappim.cashier.ui.root.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectStockFragment : Fragment(R.layout.fragment_select_stock_cashier),
    StockListClickListener {

    private val stockAdapter: SelectStockAdapter by lazy {
        SelectStockAdapter(this)
    }
    private val binding: FragmentSelectStockCashierBinding by viewBinding(
        FragmentSelectStockCashierBinding::bind
    )
    private val selectInfoProgressAdapter: SelectInfoProgressAdapter by lazy {
        SelectInfoProgressAdapter()
    }

    private val viewModel: SelectStockViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        onOutletClick()
    }

    private fun initViews() {
        with(binding) {
            buttonNext.setSafeOnClickListener {
                viewModel.saveStock(stockAdapter.getSelectedItem()!!)
                mainViewModel.stopSync()
                mainViewModel.startSync()
                findNavController().navigate(R.id.action_selectOutletFragment_to_selectCashierFragment)
            }
            swipeRefresh.setOnRefreshListener {
                viewModel.getStocks()
            }
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }

            recyclerItems.adapter = stockAdapter
            recyclerItems.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            recyclerProgress.adapter = selectInfoProgressAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stocks.collectLatest(::showStocks)
            }
        }
        selectInfoProgressAdapter.setItems(viewModel.stockProgresses)
    }

    private fun showStocks(data: Resource<List<Stock>>) {
        binding.swipeRefresh.isRefreshing = data is Resource.Loading
        when (data) {
            is Resource.Error -> {
                showToast(getErrorMessage(data.exception))
            }
            is Resource.Success -> {
                stockAdapter.addItems(data.data)
            }
        }
    }

    override fun onOutletClick() {
        binding.buttonNext.isEnabled = stockAdapter.getSelectedItem() != null
    }
}