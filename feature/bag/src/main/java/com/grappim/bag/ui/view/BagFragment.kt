package com.grappim.bag.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.bag.di.BagComponent
import com.grappim.bag.di.DaggerBagComponent
import com.grappim.bag.ui.viewmodel.BagViewModel
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.uikit.theme.CashierTheme

class BagFragment : BaseFragment<BagViewModel>() {

    private val bagComponent: BagComponent by lazy {
        DaggerBagComponent
            .builder()
            .bagDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        bagComponent.multiViewModelFactory()
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