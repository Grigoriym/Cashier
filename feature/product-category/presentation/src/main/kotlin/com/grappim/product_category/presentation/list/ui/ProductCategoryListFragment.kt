package com.grappim.product_category.presentation.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.product_category.presentation.list.di.DaggerProductCategoryListComponent
import com.grappim.product_category.presentation.list.di.ProductCategoryListComponent
import com.grappim.uikit.theme.CashierTheme

class ProductCategoryListFragment : BaseFragment<ProductCategoryListViewModel>() {

    private val productCategoryListComponent: ProductCategoryListComponent by lazy {
        DaggerProductCategoryListComponent
            .builder()
            .productCategoryListDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        productCategoryListComponent.multiViewModelFactory()
    }

    override val viewModel: ProductCategoryListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                ProductCategoryListFragmentScreen()
            }
        }
    }

    @Composable
    private fun ProductCategoryListFragmentScreen() {
        ProductCategoryListScreen(
            onBackPressed = viewModel::onBackPressed,
            onCreateCategoryClick = viewModel::goToCategoryCreate,
            categories = emptyList(),
            onCategoryClick = viewModel::goToCategoryDetails
        )
    }
}