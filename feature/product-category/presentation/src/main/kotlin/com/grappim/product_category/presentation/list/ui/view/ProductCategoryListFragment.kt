package com.grappim.product_category.presentation.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.product_category.presentation.list.di.DaggerProductCategoryListComponent
import com.grappim.product_category.presentation.list.di.ProductCategoryListComponent
import com.grappim.product_category.presentation.list.ui.viewmodel.ProductCategoryListViewModel
import com.grappim.uikit.compose.LoaderDialogCompose
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
        val categories by viewModel.categories.collectAsState()
        val loading by viewModel.loading.observeAsState(false)

        LoaderDialogCompose(show = loading)

        ProductCategoryListScreen(
            onBackPressed = viewModel::closeFlow,
            onCreateCategoryClick = viewModel::goToCategoryCreate,
            categories = categories,
            onCategoryClick = viewModel::goToCategoryDetails
        )
    }
}