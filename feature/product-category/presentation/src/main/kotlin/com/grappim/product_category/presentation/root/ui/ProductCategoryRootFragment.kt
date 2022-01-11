package com.grappim.product_category.presentation.root.ui

import android.content.Context
import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.product_category.presentation.R
import com.grappim.product_category.presentation.root.di.DaggerProductCategoryRootComponent
import com.grappim.product_category.presentation.root.di.ProductCategoryRootComponent
import javax.inject.Inject

class ProductCategoryRootFragment : BaseFragment<ProductCategoryRootViewModel>(
    R.layout.fragment_product_categories_root
), HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private var _productCategoryRootComponent: ProductCategoryRootComponent? = null
    val productCategoryRootComponent
        get() = requireNotNull(_productCategoryRootComponent)

    override val viewModel: ProductCategoryRootViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _productCategoryRootComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _productCategoryRootComponent = DaggerProductCategoryRootComponent
            .factory()
            .create(findComponentDependencies())
        productCategoryRootComponent.inject(this)
    }

}