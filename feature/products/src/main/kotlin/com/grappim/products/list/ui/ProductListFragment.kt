package com.grappim.products.list.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.products.list.di.DaggerProductsListComponent
import com.grappim.products.list.di.ProductsListComponent
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class ProductListFragment : BaseFragment<ProductListViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: ProductListViewModel by viewModels {
        viewModelFactory
    }

    private var _productListComponent: ProductsListComponent? = null
    private val productListComponent
        get() = requireNotNull(_productListComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _productListComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _productListComponent = DaggerProductsListComponent
            .builder()
            .productListDeps(findComponentDependencies())
            .build()
        productListComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                ProductsFragmentScreen()
            }
        }
    }

    @Composable
    private fun ProductsFragmentScreen() {
        val categories by viewModel.categories.collectAsState()
        val products by viewModel.products.collectAsState(initial = emptyList())
        val searchQuery by viewModel.query.collectAsState()
        val index by viewModel.selectedIndex.collectAsState()

        ProductsScreen(
            onBackPressed = viewModel::onBackPressed,
            onCreateProductClick = viewModel::showCreateProduct,
            searchText = searchQuery,
            setSearchText = viewModel::searchProducts,
            categories = categories,
            selectedIndex = index,
            onTabClick = { idx, category ->
                viewModel.setCategory(category, idx)
            },
            products = products,
            onProductClick = viewModel::showEditProduct
        )
    }
}