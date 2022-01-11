package com.grappim.waybill.ui.product.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.core.BaseFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.common.lce.Try
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.ui.product.di.DaggerWaybillProductComponent
import com.grappim.waybill.ui.product.di.WaybillProductComponent
import com.grappim.waybill.ui.root.ui.WaybillRootViewModel
import java.text.DecimalFormat
import javax.inject.Inject

class WaybillProductFragment : BaseFragment<WaybillProductViewModel>() {

    companion object {
        const val ARG_WAYBILL_ID = "arg_waybill_id"
        const val ARG_PRODUCT = "arg_product"
        const val ARG_WAYBILL_PRODUCT = "arg_waybill_product"
        const val ARG_BARCODE = "arg_barcode"
    }

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: WaybillProductViewModel by viewModels {
        viewModelFactory
    }

    private val product: Product? by lazyArg(ARG_PRODUCT)
    private val waybillId: Int by lazyArg(ARG_WAYBILL_ID)
    private val waybillProduct: WaybillProduct? by lazyArg(ARG_WAYBILL_PRODUCT)
    private val barcode: String? by lazyArg(ARG_BARCODE)

    private var _waybillProductComponent: WaybillProductComponent? = null
    private val waybillProductComponent
        get() = requireNotNull(_waybillProductComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _waybillProductComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _waybillProductComponent = DaggerWaybillProductComponent
            .builder()
            .waybillProductDeps(findComponentDependencies())
            .build()
        waybillProductComponent.inject(this)
    }

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
        val sharedViewModel by viewModels<WaybillRootViewModel>()

        viewModel.setWaybillProductState(
            product = product,
            waybillId = waybillId,
            waybillProduct = waybillProduct,
            barcode = barcode
        )
        val waybillProduct by viewModel.waybillProductState

        val productCreatedState by viewModel.productCreated
        when (val state = productCreatedState) {
            is com.grappim.common.lce.Try.Error -> {
                showToast(getErrorMessage(state.exception))
            }
        }

        WaybillProductScreen(
            waybillProductStates = waybillProduct,
            onBackClick = sharedViewModel::onBackPressed,
            onActionClick = viewModel::waybillProductAction,
            setBarcode = viewModel::setBarcode,
            setProductName = viewModel::setWaybillProductName,
            setAmount = viewModel::setAmount,
            setPurchasePrice = viewModel::setPurchasePrice,
            setSellingPrice = viewModel::setSellingPrice
        )
    }
}