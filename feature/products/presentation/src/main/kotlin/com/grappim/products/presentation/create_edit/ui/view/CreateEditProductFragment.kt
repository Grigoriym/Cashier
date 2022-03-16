package com.grappim.products.presentation.create_edit.ui.view

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
import com.grappim.core.base.BaseFragment2
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.domain.model.product.Product
import com.grappim.navigation.FlowRouter
import com.grappim.products.presentation.BundleArgsKeys
import com.grappim.products.presentation.create_edit.di.CreateEditProductComponent
import com.grappim.products.presentation.create_edit.di.DaggerCreateEditProductComponent
import com.grappim.products.presentation.create_edit.ui.viewmodel.CreateEditProductViewModel
import com.grappim.products.presentation.create_edit.ui.viewmodel.CreateEditProductViewModelImpl
import com.grappim.products.presentation.model.CreateEditFlow
import com.grappim.products.presentation.root.ui.ProductsRootViewModel
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class CreateEditProductFragment : BaseFragment2<CreateEditProductViewModel>() {

    private val component: CreateEditProductComponent by lazy {
        DaggerCreateEditProductComponent
            .builder()
            .createEditProductDeps(findComponentDependencies())
            .build()
    }

    private val rootViewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    private val viewModelFactory: CreateEditProductViewModelImpl.Factory by lazy {
        component.provideCreateProductAssistedViewModelFactory()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val createEditFlow by lazyArg<CreateEditFlow>(BundleArgsKeys.ARG_KEY_FLOW)
    private val product by lazyArg<Product?>(BundleArgsKeys.ARG_KEY_PRODUCT)

    override val viewModel: CreateEditProductViewModel by assistedViewModel {
        viewModelFactory.create(
            createEditFlow = createEditFlow,
            productToEdit = product
        )
    }

    private val rootViewModel by viewModels<ProductsRootViewModel>(
        ownerProducer = {
            requireParentFragment()
        },
        factoryProducer = {
            rootViewModelFactory
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                CreateEditProductFragmentScreen()
            }
        }
    }

    @Composable
    private fun CreateEditProductFragmentScreen() {
        val scannedBarcode by rootViewModel.scannedBarcode.observeAsState()
        scannedBarcode?.let {
            viewModel.setBarcode(it)
        }

        val categoryList by viewModel.categoriesFlow.collectAsState()
        val selectedCategory by viewModel.selectedCategory.collectAsState()
        val productName by viewModel.productName.collectAsState()
        val productUnits by viewModel.productUnits.collectAsState()
        val selectedUnit by viewModel.selectedUnit.collectAsState()
        val barcode by viewModel.barcode.collectAsState()
        val purchasePrice by viewModel.purchasePrice.collectAsState()
        val sellingPrice by viewModel.sellingPrice.collectAsState()
        val markup by viewModel.markup.collectAsState()
        val amountAndUnit by viewModel.amountAndUnit.collectAsState()
        val dropDownExpanded by viewModel.dropDownExpanded.collectAsState()
        val loading by viewModel.loading.observeAsState(false)

        LoaderDialogCompose(show = loading)

        CreateEditProductScreen(
            onBackPressed = viewModel::onBackPressed3,
            onCreateProductClick = viewModel::createEditProduct,
            productName = productName,
            setProductName = viewModel::setProductName,
            units = productUnits,
            selectedUnit = selectedUnit,
            onUnitChanged = viewModel::setProductUnit,
            onMinusClick = viewModel::subtractQuantity,
            onPlusClick = viewModel::addQuantity,
            quantityAndUnitText = amountAndUnit,
            purchasePrice = purchasePrice,
            setPurchasePrice = viewModel::setPurchasePrice,
            extraPrice = markup,
            setExtraPrice = viewModel::setMarkup,
            sellingPrice = sellingPrice,
            setSellingPrice = viewModel::setSellingPrice,
            barcode = barcode,
            setBarcode = viewModel::setBarcode,
            dropDownExpanded = dropDownExpanded,
            onDismissDropDown = viewModel::dismissDropDown,
            onDropDownClick = viewModel::onDropDownExpand,
            categoryItems = categoryList,
            onCategoryClick = viewModel::selectCategory,
            createEditFlow = createEditFlow,
            selectedCategory = selectedCategory,
            onScanClick = viewModel::goToScanner
        )
    }

    companion object {
        fun newInstance(args: Bundle) =
            CreateEditProductFragment().apply {
                arguments = args
            }
    }
}