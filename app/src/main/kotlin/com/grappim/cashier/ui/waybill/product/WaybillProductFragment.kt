package com.grappim.cashier.ui.waybill.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.cashier.R
import com.grappim.cashier.core.delegate.lazyArg
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.ui.theme.CashierTheme
import com.grappim.cashier.ui.waybill.details.WaybillDetailsFragment
import com.grappim.db.entity.ProductEntity
import com.grappim.domain.base.Result
import com.grappim.domain.model.waybill.WaybillProduct
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class WaybillProductFragment : Fragment() {

    companion object {
        const val ARG_WAYBILL_ID = "arg_waybill_id"
        const val ARG_PRODUCT = "arg_product"
        const val ARG_WAYBILL_PRODUCT = "arg_waybill_product"
        const val ARG_BARCODE = "arg_barcode"
    }

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val product: ProductEntity? by lazyArg(ARG_PRODUCT)
    private val waybillId: Int by lazyArg(ARG_WAYBILL_ID)
    private val waybillProduct: WaybillProduct? by lazyArg(ARG_WAYBILL_PRODUCT)
    private val barcode: String? by lazyArg(ARG_BARCODE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                WaybillProductFragmentScreen()
            }
        }
    }

    @Composable
    private fun WaybillProductFragmentScreen() {
        val viewModel = viewModel<WaybillProductViewModel>()
        viewModel.setWaybillProductState(
            productEntity = product,
            waybillId = waybillId,
            waybillProduct = waybillProduct,
            barcode = barcode
        )
        val waybillProduct by viewModel.waybillProductState

        val productCreatedState by viewModel.productCreated
        when (val state = productCreatedState) {
            is Result.Success -> {
                findNavController()
                    .navigate(
                        R.id.action_waybillProduct_to_waybillDetails,
                        bundleOf(
                            WaybillDetailsFragment.ARG_TOTAL_COST to state.data
                        )
                    )
            }
            is Result.Error -> {
                showToast(getErrorMessage(state.exception))
            }
        }

        WaybillProductScreen(
            waybillProductStates = waybillProduct,
            onBackClick = { findNavController().popBackStack() },
            onActionClick = {
                viewModel.waybillProductAction()
            },
            setBarcode = viewModel::setBarcode,
            setProductName = viewModel::setWaybillProductName,
            setAmount = viewModel::setAmount,
            setPurchasePrice = viewModel::setPurchasePrice,
            setSellingPrice = viewModel::setSellingPrice
        )
    }
}