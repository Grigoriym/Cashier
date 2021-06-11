package com.grappim.cashier.ui.selectinfo.stock

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.databinding.FragmentSelectStockCashierBinding
import com.grappim.cashier.ui.root.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.stocks.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it is Resource.Loading
            when (it) {
                is Resource.Error -> {
                    showToast(getErrorMessage(it.exception))
                }
                is Resource.Success -> {
                    stockAdapter.addItems(it.data)
                }
            }
        }
        selectInfoProgressAdapter.setItems(viewModel.stockProgresses)
    }

    override fun onOutletClick() {
        binding.buttonNext.isEnabled = stockAdapter.getSelectedItem() != null
    }
}