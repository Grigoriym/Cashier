package com.grappim.feature.bag.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.cashier.core.base.BaseFlowFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.feature.bag.presentation.di.BagComponent
import com.grappim.feature.bag.presentation.di.DaggerBagComponent
import com.grappim.feature.bag.presentation.ui.viewmodel.BagViewModel
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.theme.CashierTheme

class BagFragment : BaseFlowFragment<BagViewModel>() {

    private val component: BagComponent by lazy {
        DaggerBagComponent
            .factory()
            .create(
                bagDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: BagViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                BagFragmentScreen()
            }
        }
    }

    @Composable
    private fun BagFragmentScreen() {
        val basketProducts by viewModel.basketProducts.collectAsState()
        val basketSum by viewModel.basketSum.collectAsState()
        val changedProduct by viewModel.changedProduct.collectAsState()

        BagScreen(
            onBackClick = viewModel::onBackPressed,
            onScanClick = viewModel::showScanner,
            onPayClick = viewModel::goToPaymentMethod,
            onMinusClick = viewModel::subtractBasketProduct,
            onPlusClick = viewModel::addProductToBasket,
            items = basketProducts,
            price = basketSum,
            changedProduct = changedProduct
        )
    }
}
