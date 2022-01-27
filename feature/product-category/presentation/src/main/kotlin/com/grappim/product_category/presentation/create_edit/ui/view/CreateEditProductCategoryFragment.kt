package com.grappim.product_category.presentation.create_edit.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.grappim.core.BaseFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.presentation.create_edit.BundleArgsKeys
import com.grappim.product_category.presentation.create_edit.di.CreateEditProductCategoryComponent
import com.grappim.product_category.presentation.create_edit.di.DaggerCreateEditProductCategoryComponent
import com.grappim.product_category.presentation.create_edit.ui.viewmodel.CreateEditProductCategoryViewModel
import com.grappim.product_category.presentation.create_edit.ui.viewmodel.CreateEditProductCategoryViewModelImpl
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

    private val categoryToEdit: ProductCategory? by lazyArg(BundleArgsKeys.ARG_KEY_EDIT_CATEGORY)
    private val args by navArgs<CreateEditProductCategoryFragmentArgs>()

    override val viewModel: CreateEditProductCategoryViewModel by assistedViewModel {
        viewModelFactory.create(
            createEditFlow = args.flow,
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
            createEditFlow = args.flow,
            onCreateCategoryClick = viewModel::createEditCategory
        )
    }
}