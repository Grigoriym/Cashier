package com.grappim.products.root.ui

import android.content.Context
import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.products.R
import com.grappim.products.root.di.DaggerProductsRootComponent
import com.grappim.products.root.di.ProductsRootComponent
import javax.inject.Inject

class ProductsRootFragment : BaseFragment<ProductsRootViewModel>(
    R.layout.fragment_products_root
), HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: ProductsRootViewModel by viewModels {
        viewModelFactory
    }

    private var _productsRootComponent: ProductsRootComponent? = null
    val productsRootComponent
        get() = requireNotNull(_productsRootComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _productsRootComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _productsRootComponent = DaggerProductsRootComponent
            .factory()
            .create(findComponentDependencies())
        productsRootComponent.inject(this)
    }
}