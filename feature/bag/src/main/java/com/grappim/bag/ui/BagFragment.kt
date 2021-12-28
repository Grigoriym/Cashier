package com.grappim.bag.ui

import android.content.Context
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
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.uikit.theme.CashierTheme

class BagFragment : BaseFragment<BagViewModel>() {

    override val viewModel: BagViewModel by viewModels {
        viewModelFactory
    }

    private var _bagComponent: BagComponent? = null
    private val bagComponent
        get() = requireNotNull(_bagComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _bagComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _bagComponent = DaggerBagComponent
            .builder()
            .bagDeps(findComponentDependencies())
            .build()
        bagComponent.inject(this)
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