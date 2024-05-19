package com.grappim.cashier.feature.paymentmethod.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.cashier.feature.paymentmethod.presentation.di.DaggerPaymentMethodComponent
import com.grappim.cashier.feature.paymentmethod.presentation.di.PaymentMethodComponent
import com.grappim.cashier.feature.paymentmethod.presentation.ui.viewmodel.PaymentMethodViewModel
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class PaymentMethodFragment : BaseFlowFragment<PaymentMethodViewModel>() {

    private val component: PaymentMethodComponent by lazy {
        DaggerPaymentMethodComponent
            .factory()
            .create(
                paymentMethodDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }
    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: PaymentMethodViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                PaymentMethodFragmentScreen()
            }
        }
    }

    @Composable
    private fun PaymentMethodFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)
        val basketSum by viewModel.basketSum.collectAsState()
        val basketCount by viewModel.basketCount.collectAsState()

        LoaderDialogCompose(show = loading)

        PaymentMethodScreen(
            onBackClick = viewModel::onBackPressed,
            itemCount = basketCount,
            basketPrice = basketSum,
            menuItems = viewModel.paymentItems,
            onItemClick = viewModel::makePayment
        )
    }
}
