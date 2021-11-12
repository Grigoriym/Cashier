package com.grappim.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BagFragment : Fragment() {

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
        val viewModel: BagViewModel = viewModel()

        val products by viewModel.products.collectAsState()
        val basketSum by viewModel.basketSum.collectAsState()

        BagScreen(
            onBackClick = viewModel::onBackPressed,
            onScanClick = viewModel::showScanner,
            onPayClick = viewModel::goToPaymentMethod,
            onMinusClick = viewModel::removeProductFromBasket,
            onPlusClick = viewModel::addProductToBasket,
            items = products,
            price = basketSum
        )
    }
}