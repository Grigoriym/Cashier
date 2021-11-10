package com.grappim.bag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.bag.databinding.FragmentBagBinding
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.domain.model.product.Product
import com.grappim.extensions.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class BagFragment : Fragment(R.layout.fragment_bag),
    BagItemClickListener {

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
                viewModel.onBackPressed()
            }
            buttonDelete.setSafeOnClickListener {
                viewModel.deleteBagProducts()
            }
            buttonScan.setSafeOnClickListener {
//                findNavController().navigate(
//                    BagFragmentDirections.actionBagFragmentToScannerFragment(
//                        ScanType.SINGLE
//                    )
//                )
            }
            buttonPay.setSafeOnClickListener {
                viewModel.goToPaymentMethod()
            }
        }
    }

    override fun addProduct(product: Product) {
        viewModel.addProductToBasket(product)
    }

    override fun removeProduct(product: Product) {
        viewModel.removeProductFromBasket(product)
    }
}