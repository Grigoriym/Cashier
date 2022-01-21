package com.grappim.product_category.presentation.root.ui

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

    private val productCategoryRootComponent: ProductCategoryRootComponent by lazy {
        DaggerProductCategoryRootComponent
            .factory()
            .create(findComponentDependencies())
    }

    override val deps: ComponentDependenciesProvider by lazy {
        productCategoryRootComponent.deps()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        productCategoryRootComponent.multiViewModelFactory()
    }

    override val viewModel: ProductCategoryRootViewModel by viewModels {
        viewModelFactory
    }
}