package com.grappim.cashier.ui.bag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.databinding.FragmentBagBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.ui.sales.SalesItemClickListener
import com.grappim.cashier.ui.scanner.ScanType
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class BagFragment : Fragment(R.layout.fragment_bag),
    SalesItemClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewBinding: FragmentBagBinding by viewBinding(FragmentBagBinding::bind)
    private val viewModel: BagViewModel by viewModels()
    private val bagAdapter: BagAdapter by lazy {
        BagAdapter(dfSimple, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) {
            bagAdapter.updateProducts(it)
        }
        viewModel.basketCount.observe(viewLifecycleOwner) {
            viewBinding.textItemsCount.text = dfSimple.format(it)
        }
        viewModel.basketSum.observe(viewLifecycleOwner) {
            viewBinding.textPrice.text = getString(
                R.string.title_price_with_currency,
                dfSimple.format(it)
            )
        }
    }

    private fun initViews() {
        with(viewBinding) {
            recyclerBag.adapter = bagAdapter
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            buttonDelete.setSafeOnClickListener {
                viewModel.deleteBagProducts()
            }
            buttonScan.setSafeOnClickListener {
                findNavController().navigate(
                    BagFragmentDirections.actionBagFragmentToScannerFragment(
                        ScanType.SINGLE
                    )
                )
            }
            buttonPay.setSafeOnClickListener {
                findNavController().navigate(BagFragmentDirections.actionBagFragmentToPaymentMethod())
            }
        }
    }

    override fun addProduct(productEntity: ProductEntity) {
        viewModel.addProductToBasket(productEntity)
    }

    override fun removeProduct(productEntity: ProductEntity) {
        viewModel.removeProductFromBasket(productEntity)
    }
}