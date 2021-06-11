package com.grappim.cashier.ui.sales

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.databinding.FragmentSalesBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class SalesFragment : Fragment(R.layout.fragment_sales), SalesItemClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewBinding: FragmentSalesBinding by viewBinding(FragmentSalesBinding::bind)
    private val salesAdapter: SalesAdapter by lazy {
        SalesAdapter(dfSimple, this)
    }
    private val viewModel: SalesViewModel by viewModels()

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
            recyclerProducts.adapter = ScaleInAnimationAdapter(salesAdapter)
            buttonBasket.setSafeOnClickListener {
                findNavController().navigate(SalesFragmentDirections.actionSalesFragmentToBagFragment())
            }
            buttonScanner.setSafeOnClickListener {
                findNavController().navigate(SalesFragmentDirections.actionSalesFragmentToScannerFragment())
            }

            editSearchProducts.doOnTextChanged { text, _, _, _ ->
                viewModel.searchProducts(text.toString())
            }
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) {
            salesAdapter.updateProducts(it)
        }
        viewModel.basketCount.observe(viewLifecycleOwner) {
            viewBinding.buttonBasket.text = dfSimple.format(it)
        }
    }

    override fun addProduct(productEntity: ProductEntity) {
        viewModel.addProductToBasket(productEntity)
    }

    override fun removeProduct(productEntity: ProductEntity) {
        viewModel.removeProductFromBasket(productEntity)
    }
}