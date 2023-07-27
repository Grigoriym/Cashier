package com.grappim.feature.waybill.presentation.ui.product.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.base.BaseFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.domain.model.Product
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.presentation.ui.product.di.DaggerWaybillProductComponent
import com.grappim.feature.waybill.presentation.ui.product.di.WaybillProductComponent
import com.grappim.feature.waybill.presentation.ui.product.ui.viewmodel.WaybillProductViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class WaybillProductFragment : BaseFragment<WaybillProductViewModel>() {

    private val component: WaybillProductComponent by lazy {
        DaggerWaybillProductComponent
            .builder()
            .waybillProductDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: WaybillProductViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val product: Product? by lazyArg(BundleArgsHelper.Waybill.ARG_KEY_PRODUCT)
    private val waybillProduct: WaybillProduct? by lazyArg(BundleArgsHelper.Waybill.ARG_KEY_WAYBILL_PRODUCT)
    private val barcode: String? by lazyArg(BundleArgsHelper.Waybill.ARG_KEY_BARCODE)

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
        viewModel.setWaybillProductState(
            product = product,
            waybillProduct = waybillProduct,
            barcode = barcode
        )
        val waybillProduct by viewModel.waybillProductState
        val loading by viewModel.loading.observeAsState(false)
        LoaderDialogCompose(show = loading)

//        val productCreatedState by viewModel.productCreated

        WaybillProductScreen(
            waybillProductStates = waybillProduct,
            onBackClick = viewModel::onBackPressed,
            onActionClick = viewModel::waybillProductAction,
            setBarcode = viewModel::setBarcode,
            setProductName = viewModel::setWaybillProductName,
            setAmount = viewModel::setAmount,
            setPurchasePrice = viewModel::setPurchasePrice,
            setSellingPrice = viewModel::setSellingPrice
        )
    }

    companion object {
        fun newInstance(args: Bundle) = WaybillProductFragment().apply {
            arguments = args
        }
    }
}
