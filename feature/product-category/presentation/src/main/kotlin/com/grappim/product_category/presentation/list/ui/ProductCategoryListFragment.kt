package com.grappim.product_category.presentation.list.ui

import android.content.Context
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
import javax.inject.Inject

class ProductCategoryListFragment : BaseFragment<ProductCategoryListViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: ProductCategoryListViewModel by viewModels {
        viewModelFactory
    }

    private var _productCategoryListComponent: ProductCategoryListComponent? = null
    private val productCategoryListComponent
        get() = requireNotNull(_productCategoryListComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _productCategoryListComponent = null
        super.onDestroy()
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

    private fun performInject() {
        _productCategoryListComponent = DaggerProductCategoryListComponent
            .builder()
            .productCategoryListDeps(findComponentDependencies())
            .build()
        productCategoryListComponent.inject(this)
    }

    @Composable
    private fun ProductCategoryListFragmentScreen() {
        ProductCategoryListScreen(
            onBackPressed = viewModel::onBackPressed,
            onCreateCategoryClick = viewModel::goToCategoryCreate,
            categories = listOf(),
            onCategoryClick = viewModel::goToCategoryDetails
        )
    }
}