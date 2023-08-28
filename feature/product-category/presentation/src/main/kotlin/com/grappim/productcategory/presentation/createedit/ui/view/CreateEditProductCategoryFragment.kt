package com.grappim.productcategory.presentation.createedit.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import com.grappim.core.base.BaseFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.navigation.router.FlowRouter
import com.grappim.productcategory.domain.model.ProductCategory
import com.grappim.productcategory.presentation.BundleArgsKeys
import com.grappim.productcategory.presentation.createedit.di.CreateEditProductCategoryComponent
import com.grappim.productcategory.presentation.createedit.di.DaggerCreateEditProductCategoryComponent
import com.grappim.productcategory.presentation.createedit.model.CreateEditFlow
import com.grappim.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModel
import com.grappim.productcategory.presentation.createedit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
import com.grappim.uikit.theme.CashierTheme

class CreateEditProductCategoryFragment : BaseFragment<CreateEditProductCategoryViewModel>() {

    private val createEditProductCategoryComponent: CreateEditProductCategoryComponent by lazy {
        DaggerCreateEditProductCategoryComponent
            .builder()
            .createEditProductCategoryDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: CreateEditProductCategoryViewModelImpl.Factory by lazy {
        createEditProductCategoryComponent.createEditViewModelAssistedFactory()
    }

    override val flowRouter: FlowRouter by lazy {
        createEditProductCategoryComponent.flowRouter()
    }

    private val categoryToEdit: ProductCategory? by lazyArg(BundleArgsKeys.ARG_KEY_EDIT_CATEGORY)
    private val flow: CreateEditFlow by lazyArg(BundleArgsKeys.ARG_KEY_FLOW)

    override val viewModel: CreateEditProductCategoryViewModel by assistedViewModel {
        viewModelFactory.create(
            createEditFlow = flow,
            productCategory = categoryToEdit
        )
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
        val categoryData by viewModel.categoryData.collectAsState()

        CreateEditProductCategoryScreen(
            onBackPressed = viewModel::onBackPressed,
            categoryName = categoryData.categoryName,
            setCategoryName = viewModel::setName,
            createEditFlow = flow,
            onCreateCategoryClick = viewModel::createEditCategory
        )
    }

    companion object {
        fun newInstance(args: Bundle?) =
            CreateEditProductCategoryFragment().apply {
                arguments = args
                    ?: bundleOf(
                        BundleArgsKeys.ARG_KEY_FLOW to CreateEditFlow.CREATE
                    )
            }
    }
}
