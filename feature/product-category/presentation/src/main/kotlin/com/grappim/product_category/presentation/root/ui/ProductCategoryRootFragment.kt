package com.grappim.product_category.presentation.root.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.product_category.presentation.BundleArgsKeys
import com.grappim.product_category.presentation.R
import com.grappim.product_category.presentation.root.di.DaggerProductCategoryRootComponent
import com.grappim.product_category.presentation.root.di.ProductCategoryRootComponent

class ProductCategoryRootFragment : BaseFlowFragment<ProductCategoryRootViewModel>(
    R.layout.fragment_product_categories_root
), HasComponentDeps {

    private val productCategoryRootComponent: ProductCategoryRootComponent by lazy {
        DaggerProductCategoryRootComponent
            .factory()
            .create(
                productCategoryRootDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val fromProduct by lazyArg<Boolean>(BundleArgsKeys.ARG_KEY_FROM_PRODUCT) {
        it as? Boolean ?: false
    }

    override val deps: ComponentDependenciesProvider by lazy {
        productCategoryRootComponent.deps()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        productCategoryRootComponent.multiViewModelFactory()
    }

    override val flowRouter: FlowRouter by lazy {
        productCategoryRootComponent.flowRouter()
    }

    override val viewModel: ProductCategoryRootViewModel by viewModels {
        viewModelFactory
    }

    override fun initialScreen() {
        if (fromProduct) {
            flowRouter.showCreateProductCategory()
        } else {
            flowRouter.showProductCategoriesList()
        }
    }

    companion object {
        fun newInstance(fromProduct: Boolean?) =
            ProductCategoryRootFragment().apply {
                arguments = bundleOf(BundleArgsKeys.ARG_KEY_FROM_PRODUCT to fromProduct)
            }
    }

}