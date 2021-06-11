package com.grappim.cashier.ui.waybill.product

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.delegate.lazyArg
import com.grappim.cashier.core.extensions.asBigDecimal
import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.databinding.FragmentWaybillProductBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.domain.waybill.WaybillProduct
import com.grappim.cashier.ui.waybill.details.WaybillDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class WaybillProductFragment : Fragment(R.layout.fragment_waybill_product) {

    companion object {
        const val ARG_WAYBILL_ID = "arg_waybill_id"
        const val ARG_PRODUCT = "arg_product"
        const val ARG_WAYBILL_PRODUCT = "arg_waybill_product"
        const val ARG_BARCODE = "arg_barcode"
    }

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val loader by lazy {
        CashierLoaderDialog(requireContext())
    }
    private val viewModel by viewModels<WaybillProductViewModel>()
    private val viewBinding by viewBinding(FragmentWaybillProductBinding::bind)

    private val product: ProductEntity? by lazyArg(ARG_PRODUCT)
    private val waybillId: Int by lazyArg(ARG_WAYBILL_ID)
    private val waybillProduct: WaybillProduct? by lazyArg(ARG_WAYBILL_PRODUCT)
    private val barcode: String? by lazyArg(ARG_BARCODE)

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
            buttonAddProduct.setSafeOnClickListener {
                if (waybillProduct != null) {
                    viewModel.updateWaybillProduct(
                        waybillId = waybillId,
                        barcode = editBarcode.text.toString(),
                        name = editName.text.toString(),
                        purchasePrice = editPurchasePrice.text.toString().asBigDecimal(),
                        sellingPrice = editSellingPrice.text.toString().asBigDecimal(),
                        amount = editAmount.text.toString().asBigDecimal(),
                        productId = waybillProduct!!.productId,
                        id = waybillProduct!!.id
                    )
                } else {
                    viewModel.createWaybillProduct(
                        waybillId = waybillId,
                        barcode = editBarcode.text.toString(),
                        name = editName.text.toString(),
                        purchasePrice = editPurchasePrice.text.toString().asBigDecimal(),
                        sellingPrice = editSellingPrice.text.toString().asBigDecimal(),
                        amount = editAmount.text.toString().asBigDecimal(),
                        productId = product?.id!!
                    )
                }
            }

            product?.let {
                editBarcode.setText(it.barcode)
                editName.setText(it.name)
                editPurchasePrice.setText(dfSimple.format(it.purchasePrice))
                editSellingPrice.setText(dfSimple.format(it.sellingPrice))
                editAmount.setText("1")
            }
            waybillProduct?.let {
                editBarcode.setText(it.barcode)
                editName.setText(it.name)
                editPurchasePrice.setText(dfSimple.format(it.purchasePrice))
                editSellingPrice.setText(dfSimple.format(it.sellingPrice))
                editAmount.setText(dfSimple.format(it.amount))
                buttonAddProduct.text = getString(
                    R.string.action_update_product
                )
            }
            if (product == null && waybillProduct == null) {
                editBarcode.setText(barcode!!)
                editName.setText(getString(R.string.title_new_product))
                editPurchasePrice.setText(dfSimple.format(bigDecimalZero()))
                editSellingPrice.setText(dfSimple.format(bigDecimalZero()))
                editAmount.setText("1")
                buttonAddProduct.text = getString(R.string.action_add_product)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.productCreated.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    findNavController()
                        .navigate(
                            R.id.action_waybillProduct_to_waybillDetails,
                            bundleOf(
                                WaybillDetailsFragment.ARG_TOTAL_COST to it.data
                            )
                        )
                }
                is Resource.Error -> {
                    showToast(getErrorMessage(it.exception))
                }
            }
        }
    }
}