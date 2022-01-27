package com.grappim.products.root.ui

import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.products.R
import com.grappim.products.root.di.DaggerProductsRootComponent
import com.grappim.products.root.di.ProductsRootComponent

class ProductsRootFragment : BaseFragment<ProductsRootViewModel>(
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
}