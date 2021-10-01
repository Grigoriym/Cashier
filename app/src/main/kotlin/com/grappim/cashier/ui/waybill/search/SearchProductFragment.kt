package com.grappim.cashier.ui.waybill.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.extensions.setSafeOnClickListener
import com.grappim.extensions.showToast
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.databinding.FragmentSearchProductBinding
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.cashier.ui.products.ProductsAdapter
import com.grappim.cashier.ui.products.ProductsClickListener
import com.grappim.cashier.ui.waybill.product.WaybillProductFragment
import com.grappim.domain.base.Result
import com.grappim.domain.model.product.Product
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class SearchProductFragment : Fragment(R.layout.fragment_search_product),
    ProductsClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewBinding by viewBinding(FragmentSearchProductBinding::bind)
    private val loader by lazy {
        CashierLoaderDialog(requireContext())
    }
    private val viewModel by viewModels<SearchProductViewModel>()
    private val productsAdapter: ProductsAdapter by lazy {
        ProductsAdapter(dfSimple, this)
    }
    private val args by navArgs<SearchProductFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(viewBinding) {
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            editSearchProducts.doAfterTextChanged {
                viewModel.searchProducts(it.toString())
            }
            recyclerProducts.adapter = productsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.updateProducts(it)
        }
        viewModel.product.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Result.Loading)
            when (it) {
                is Result.Success -> {
                    findNavController().navigate(
                        R.id.action_search_to_waybillProduct,
                        bundleOf(
                            WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
                            WaybillProductFragment.ARG_PRODUCT to it.data
                        )
                    )
                }
                is Result.Error -> {
                    showToast(getString(R.string.waybill_error_no_product))
                }
            }
        }
        viewModel.waybillProduct.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Result.Loading)
            when (it) {
                is Result.Success -> {
                    findNavController()
                        .navigate(
                            R.id.action_search_to_waybillProduct,
                            bundleOf(
                                WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
                                WaybillProductFragment.ARG_WAYBILL_PRODUCT to it.data
                            )
                        )
                }
            }
        }
    }

    override fun onProductClick(product: Product) {
        viewModel.checkProductInWaybill(
            product.barcode,
            args.waybillId
        )
    }
}