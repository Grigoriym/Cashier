package com.grappim.products.presentation.root.ui

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.navigation.router.FlowRouter
import com.grappim.products.presentation.R
import com.grappim.products.presentation.root.di.DaggerProductsRootComponent
import com.grappim.products.presentation.root.di.ProductsRootComponent

class ProductsRootFragment : BaseFlowFragment<ProductsRootViewModel>(
    R.layout.fragment_products_root
), HasComponentDeps {

    private val productsRootComponent: ProductsRootComponent by lazy {
        DaggerProductsRootComponent
            .factory()
            .create(
                productsRootDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    override val deps: ComponentDependenciesProvider by lazy {
        productsRootComponent.deps()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        productsRootComponent.multiViewModelFactory()
    }

    override val viewModel: ProductsRootViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        productsRootComponent.flowRouter()
    }

    override fun initialScreen() {
        flowRouter.showProductsList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(
            BundleArgsHelper.ProductScannerArgs.PRODUCT_SCANNER_REQUEST_KEY
        ) { requestKey, bundle ->
            val result = bundle.getString(
                BundleArgsHelper.ProductScannerArgs.ARG_KEY_SCANNED_BARCODE
            )
            result?.let {
                viewModel.setBarcode(result)
            }
        }
    }
}
