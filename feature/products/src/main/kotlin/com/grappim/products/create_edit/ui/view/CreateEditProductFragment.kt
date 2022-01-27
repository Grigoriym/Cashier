package com.grappim.products.create_edit.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import com.grappim.core.BaseFragment
import com.grappim.core.delegate.lazyArg
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.domain.model.product.Product
import com.grappim.products.BundleArgsKeys
import com.grappim.products.create_edit.di.CreateEditProductComponent
import com.grappim.products.create_edit.di.DaggerCreateEditProductComponent
import com.grappim.products.create_edit.ui.viewmodel.CreateEditProductViewModel
import com.grappim.products.create_edit.ui.viewmodel.CreateEditProductViewModelImpl
import com.grappim.products.model.CreateEditFlow
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class CreateEditProductFragment : BaseFragment<CreateEditProductViewModel>() {

    private val createComponent: CreateEditProductComponent by lazy {
        DaggerCreateEditProductComponent
            .builder()
            .createEditProductDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: CreateEditProductViewModelImpl.Factory by lazy {
        createComponent.provideCreateProductAssistedViewModelFactory()
    }

    private val createEditFlow by lazyArg<CreateEditFlow>(BundleArgsKeys.ARG_KEY_FLOW)
    private val product by lazyArg<Product?>(BundleArgsKeys.ARG_KEY_PRODUCT)

    override val viewModel: CreateEditProductViewModel by assistedViewModel {
        viewModelFactory.create(
            createEditFlow = createEditFlow,
            productToEdit = product,
            scannedBarcode = null
        )
    }

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
            onBackPressed = viewModel::onBackPressed,
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
            onScanClick = {
//                findNavController().navigate(
//                    CreateEditProductFragmentDirections.actionCreateProductToScanner()
//                )
            }
        )
    }
}