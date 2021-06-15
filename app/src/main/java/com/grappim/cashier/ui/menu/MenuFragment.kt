package com.grappim.cashier.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu), MenuItemClickListener {

    @Inject
    lateinit var generalStorage: GeneralStorage

    private val viewModel by viewModels<MenuViewModel>()
    private val menuItemsAdapter: MenuAdapter by lazy {
        MenuAdapter(this)
    }
    private val viewBinding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(viewBinding) {
            recyclerMenu.adapter = menuItemsAdapter
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            textCashier.text = generalStorage.getCashierName()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.menuItems.collectLatest { menuItems ->
                menuItemsAdapter.setItems(menuItems)
            }
        }
    }

    override fun onItemClick(item: MenuItem) {
        when (item.type) {
            MenuItemType.ACCEPTANCE -> {
                findNavController().navigate(R.id.action_menuFragment_to_acceptanceFragment)
            }
            MenuItemType.PRODUCTS -> {
                findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
            }
            MenuItemType.SALES -> {
                findNavController().navigate(R.id.action_menuFragment_to_salesFragment)
            }
        }
    }

}