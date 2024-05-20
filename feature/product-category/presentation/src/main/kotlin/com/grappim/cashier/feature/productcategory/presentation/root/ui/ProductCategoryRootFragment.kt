package com.grappim.cashier.feature.productcategory.presentation.root.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.deps.HasComponentDeps
import com.grappim.cashier.core.base.BaseFlowFragment
import com.grappim.cashier.core.delegate.lazyArg
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.feature.productcategory.presentation.BundleArgsKeys
import com.grappim.cashier.feature.productcategory.presentation.R
import com.grappim.cashier.feature.productcategory.presentation.root.di.DaggerProductCategoryRootComponent
import com.grappim.cashier.feature.productcategory.presentation.root.di.ProductCategoryRootComponent
import com.grappim.navigation.router.FlowRouter

class ProductCategoryRootFragment :
    BaseFlowFragment<ProductCategoryRootViewModel>(R.layout.fragment_product_categories_root),
    HasComponentDeps {

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
        fun newInstance(fromProduct: Boolean?) = ProductCategoryRootFragment().apply {
            arguments = bundleOf(BundleArgsKeys.ARG_KEY_FROM_PRODUCT to fromProduct)
        }
    }
}
