package com.grappim.cashier.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.databinding.FragmentProductsBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.widget.textChanges
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products),
    ProductsClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewModel: ProductsViewModel by viewModels()
    private val binding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)
    private val productsAdapter: ProductsAdapter by lazy {
        ProductsAdapter(dfSimple, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(binding) {
            buttonMenu.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            buttonEditCreateProduct.setSafeOnClickListener {
                findNavController().navigate(ProductsFragmentDirections.actionProductsToCreateProduct())
            }
            recyclerProducts.adapter = ScaleInAnimationAdapter(productsAdapter)
            tabsCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewModel.setCategory(tab?.tag as CategoryEntity)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })

            editSearchProducts
                .textChanges()
                .onEach {
                    viewModel.searchProducts(it.toString())
                }.launchIn(viewLifecycleOwner.lifecycleScope)

        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner, ::showCategories)
        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.updateProducts(it)
        }
    }

    private fun showCategories(categories: List<CategoryEntity>) {
        binding.tabsCategories.removeAllTabs()
        categories.forEach {
            val tab = binding.tabsCategories.newTab().apply {
                text = it.name
                tag = it
            }
            binding.tabsCategories.addTab(tab)
        }
    }

    override fun onProductClick(productEntity: ProductEntity) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsToEditProduct(product = productEntity)
        )
    }

}