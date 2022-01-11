package com.grappim.product_category.presentation.create_edit.ui

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
import com.grappim.product_category.presentation.create_edit.di.CreateEditProductCategoryComponent
import com.grappim.product_category.presentation.create_edit.di.DaggerCreateEditProductCategoryComponent
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class CreateEditProductCategoryFragment : BaseFragment<CreateEditProductCategoryViewModel>() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: CreateEditProductCategoryViewModel by viewModels {
        viewModelFactory
    }

    private var _createEditProductCategoryComponent: CreateEditProductCategoryComponent? = null
    private val createEditProductCategoryComponent
        get() = requireNotNull(_createEditProductCategoryComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _createEditProductCategoryComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _createEditProductCategoryComponent = DaggerCreateEditProductCategoryComponent
            .builder()
            .createEditProductCategoryDeps(findComponentDependencies())
            .build()
        createEditProductCategoryComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                CreateEditProductCategoryFragmentScreen()
            }
        }
    }

    @Composable
    private fun CreateEditProductCategoryFragmentScreen() {
        CreateEditProductCategoryScreen(
            onBackPressed = viewModel::onBackPressed
        )
    }
}