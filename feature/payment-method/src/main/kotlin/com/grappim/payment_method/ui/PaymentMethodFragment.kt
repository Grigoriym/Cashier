package com.grappim.payment_method.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.common.lce.Try
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.payment_method.di.DaggerPaymentMethodComponent
import com.grappim.payment_method.di.PaymentMethodComponent
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class PaymentMethodFragment : BaseFragment<PaymentMethodViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: PaymentMethodViewModel by viewModels {
        viewModelFactory
    }

    private var _paymentMethodComponent: PaymentMethodComponent? = null
    private val paymentMethodComponent
        get() = requireNotNull(_paymentMethodComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _paymentMethodComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _paymentMethodComponent = DaggerPaymentMethodComponent
            .builder()
            .paymentMethodDeps(findComponentDependencies())
            .build()
        paymentMethodComponent.inject(this)
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

    private fun showPaymentStatusResult(data: com.grappim.common.lce.Try<Unit>) {
        when (data) {
            is com.grappim.common.lce.Try.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

    @Composable
    private fun PaymentMethodFragmentScreen() {
        val viewModel: PaymentMethodViewModel = viewModel()

        val basketSum by viewModel.basketSum.collectAsState()
        val basketCount by viewModel.basketCount.collectAsState()

        val paymentStatus by viewModel.paymentStatus.collectAsState()

        LoaderDialogCompose(show = paymentStatus is com.grappim.common.lce.Try.Loading)

        LaunchedEffect(key1 = paymentStatus) {
            showPaymentStatusResult(paymentStatus)
        }

        PaymentMethodScreen(
            onBackClick = viewModel::onBackPressed,
            itemCount = basketCount,
            basketPrice = basketSum,
            menuItems = viewModel.paymentItems,
            onItemClick = viewModel::makePayment
        )
    }

}