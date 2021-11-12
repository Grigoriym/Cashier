package com.grappim.payment_method

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grappim.domain.base.Result
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodFragment : Fragment() {

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

    private fun showPaymentStatusResult(data: Result<Unit>) {
        when (data) {
            is Result.Error -> {
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

        LoaderDialogCompose(show = paymentStatus is Result.Loading)

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